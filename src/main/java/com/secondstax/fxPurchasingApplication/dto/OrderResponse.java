package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.OrderStatus;
import com.secondstax.fxPurchasingApplication.model.BankAccount;

public record OrderResponse  (String trader_Email,
                              String provider,
                              BankAccount bankAccount,
                              String exchange,
                              OrderStatus status
                              ){ }
