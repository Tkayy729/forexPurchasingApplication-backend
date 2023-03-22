package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.model.BankAccount;

public record OrderRequest(
        String trader_Email,
        String provider,
        BankAccount bankAccount,
        String exchange
) { }
