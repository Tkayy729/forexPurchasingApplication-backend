package com.secondstax.fxPurchasingApplication.dto;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.model.Trader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountRequest {
    private String name;

    private String accountNumber;

    private String branchName;

    private Currency currency;

}

