package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.OrderStatus;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import com.secondstax.fxPurchasingApplication.model.BankAccount;

public record OrderResponse(Long orderId, String trader_Email, Provider provider, BankAccount bankAccount, Currency exchange,
                            Double amount, OrderStatus status) {
}
