package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;
import com.secondstax.fxPurchasingApplication.dto.OrderResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;


public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest, @AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException;
}
