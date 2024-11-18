package com.io.ziblox.CinePass.controllers;

import com.io.ziblox.CinePass.dtos.OrderDetailDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/order_details") //Đánh version cho API giúp nâng cấp dễ dàng hơn
public class OrderDetailController {
    //Hiển thị danh sách các chi tiết đơn hàng
    @GetMapping("") //http://localhost:8088/api/v1/order_details?page=1&limit=10
    public ResponseEntity<?> getOrderDetails(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok().body("This is a list of Order Detail details in page " + page + " with limit " + limit);
    }

    //Hiển thị thông tin chi tiết một chi tiết đơn hàng theo id
    @GetMapping("/{id}") //http://localhost:8088/api/v1/order_details/1
    public ResponseEntity<?> getOrderDetailById(
            @PathVariable("id") int id
    ){
        return ResponseEntity.ok().body("This is a Order Detail with id " + id);
    }

    //Hiển thị danh sách các chi tiết đơn hàng theo order_id
    @GetMapping("/order/{order_id}") //http://localhost:8088/api/v1/order_details/order/1?page=1&limit=10
    public ResponseEntity<?> getOrderDetailsByOrderId(
            @PathVariable("order_id") int orderId,
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok().body("This is a list of Order Details with order_id " + orderId + " in page " + page + " with limit " + limit);
    }

    //Thêm một chi tiết đơn hàng mới
    @PostMapping("")
    public ResponseEntity<?> createOrder(
            @Valid
            @RequestBody OrderDetailDto orderDetailDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.status(201).body("This is a new Order Detail");
    }

    //Sửa thông tin một chi tiết đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @PathVariable("id") int id,
            @Valid
            @RequestBody OrderDetailDto orderDetailDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList());
        }
        return ResponseEntity.ok().body("Updated Order Detail with id " + id);
    }

    //Xóa một chi tiết đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetail(
            @Valid
            @PathVariable("id") int id
    ) {
        return ResponseEntity.status(204).body("Deleted Order Detail with id " + id);
    }
}
