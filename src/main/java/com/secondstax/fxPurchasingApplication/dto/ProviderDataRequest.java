package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ProviderDataRequest(
        Double amount,
        Provider provider,
        Currency currency
) {
}
