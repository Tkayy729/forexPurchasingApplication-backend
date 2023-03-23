package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.Provider;

public record OrderResponse(String trader_Email,
                            Provider provider,
                            Long bankAccountId,
                            Currency exchange,
                            Double amount
) {
}
