package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;
import com.secondstax.fxPurchasingApplication.dto.OrderResponse;


public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
