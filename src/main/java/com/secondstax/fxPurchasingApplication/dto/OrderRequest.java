package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import com.secondstax.fxPurchasingApplication.model.BankAccount;

public record OrderRequest(
        Provider provider,
        Long bankAccountId,
        Currency exchange,
        Double amount
) { }
