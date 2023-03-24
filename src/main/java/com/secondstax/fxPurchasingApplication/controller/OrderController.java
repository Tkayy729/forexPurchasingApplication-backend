package com.secondstax.fxPurchasingApplication.controller;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;
import com.secondstax.fxPurchasingApplication.dto.OrderResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest, @AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest, userDetails));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId,@AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOne(orderId,userDetails));
    }

    @GetMapping("")
    public ResponseEntity<List<OrderResponse>> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersOfTrader(userDetails));
    }

}
