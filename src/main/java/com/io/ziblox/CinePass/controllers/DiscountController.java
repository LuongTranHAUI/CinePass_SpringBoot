package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.dtos.DiscountDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.models.Discount;
import com.io.ziblox.CinePass.services.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/discounts") //Đánh version cho API giúp nâng cấp dễ dàng hơn
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    //Thêm một mã giảm giá mới
        @PostMapping("") //http://localhost:8088/api/v1/discounts
    public ResponseEntity<?> createDiscount(
            @Valid
            @RequestBody DiscountDto discountDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        discountService.createDiscount(discountDto);
        return ResponseEntity.status(201).body("Create a new discount successfully");
    }

    //Hiển thị danh sách các mã giảm giá
    @GetMapping("") //http://localhost:8088/api/v1/discounts?page=1&limit=10
    public ResponseEntity<?> getDiscounts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Discount> discounts = discountService.getAllDiscount();
        return ResponseEntity.ok().body(discounts);
    }

    //Hiển thị thông tin chi tiết một mã giảm giá theo id
    @GetMapping("/{id}") //http://localhost:8088/api/v1/discounts/1
    public ResponseEntity<?> getDiscountById(
            @PathVariable("id") int id
    ) throws DataNotFoundException {
        Discount discount = discountService.getDiscountById(id);
        return ResponseEntity.ok().body(discount);
    }

    //Sửa thông tin một mã giảm giá
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(
            @PathVariable("id") int id,
            @Valid
            @RequestBody DiscountDto discountDto,
            BindingResult bindingResult
    ) throws DataNotFoundException {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        discountService.updateDiscount(id, discountDto);
        return ResponseEntity.ok().body("Updated discount with id " + id);
    }

    //Xóa một mã giảm giá
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(
            @PathVariable("id") int id
    ) {
        discountService.deleteDiscount(id);
        return ResponseEntity.ok().body("Deleted discount with id " + id);
    }
}
