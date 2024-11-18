package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.dtos.TheaterDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/theaters") //Đánh version cho API giúp nâng cấp dễ dàng hơn
public class TheaterController {
    //Hiển thị danh sách các rạp chiếu phim
    @GetMapping("") //http://localhost:8088/api/v1/theaters?page=1&limit=10
    public ResponseEntity<?> getTheaters(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok().body("This is a list of theaters in page " + page + " with limit " + limit);
    }

    //Hiển thị thông tin chi tiết một rạp chiếu phim theo id
    @GetMapping("/{id}") //http://localhost:8088/api/v1/theaters/1
    public ResponseEntity<?> getTheaterById(
            @PathVariable("id") int id
    ){
        return ResponseEntity.ok().body("This is a theater with id " + id);
    }

    //Thêm một rạp chiếu phim mới
    @PostMapping("")
    public ResponseEntity<?> createTheater(
            @Valid
            @RequestBody TheaterDto theaterDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.status(201).body("This is a new theater");
    }

    //Sửa thông tin một rạp chiếu phim
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTheater(
            @PathVariable("id") int id,
            @Valid
            @RequestBody TheaterDto theaterDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.ok().body("Updated theater with id " + id);
    }

    //Xóa một rạp chiếu phim
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheater(
            @Valid
            @PathVariable("id") int id
    ) {
        return ResponseEntity.status(204).body("Deleted theater with id " + id);
    }
}