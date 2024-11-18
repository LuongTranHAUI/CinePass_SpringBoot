package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.dtos.OrderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/orders") //Đánh version cho API giúp nâng cấp dễ dàng hơn
public class OrderController {
    //Hiển thị danh sách các đơn hàng
    @GetMapping("") //http://localhost:8088/api/v1/orders?page=1&limit=10
    public ResponseEntity<?> getOrders(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok().body("This is a list of orders in page " + page + " with limit " + limit);
    }

    //Hiển thị thông tin chi tiết một đơn hàng theo id
    @GetMapping("/{id}") //http://localhost:8088/api/v1/orders/1
    public ResponseEntity<?> getOrderById(
            @PathVariable("id") int id
    ){
        return ResponseEntity.ok().body("This is a Order with id " + id);
    }

    //Hiển thị danh sách các đơn hàng theo user_id
    @GetMapping("/user/{user_id}") //http://localhost:8088/api/v1/orders/user/1?page=1&limit=10
    public ResponseEntity<?> getOrdersByUserId(
            @PathVariable("user_id") int userId,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok().body("This is a list of orders with user_id " + userId + " in page " + page + " with limit " + limit);
    }

    //Thêm một đơn hàng mới
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid
            @RequestBody OrderDto orderDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.status(201).body("This is a new Order");
    }

    //Sửa thông tin một đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(
            @PathVariable("id") int id,
            @Valid
            @RequestBody OrderDto orderDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.ok().body("Updated Order with id " + id);
    }

    //Xóa một đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(
            @Valid
            @PathVariable("id") int id
    ) {
        return ResponseEntity.status(204).body("Deleted Order with id " + id);
    }
}
