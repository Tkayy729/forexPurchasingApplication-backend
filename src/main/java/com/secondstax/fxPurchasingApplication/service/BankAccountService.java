package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.dto.BankAccountRequest;
import com.secondstax.fxPurchasingApplication.dto.BankAccountResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface BankAccountService {
    BankAccountResponse addBankAccount(BankAccountRequest bankAccountRequest, UserDetails userDetails);

    List<BankAccountResponse> getAllBankAccountOfTrader(UserDetails userDetails) throws ResourceNotFoundException;
    public BankAccountResponse getAccount(Long bankAccountId,  @AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException;

    BankAccount findAccount(Long bankAccountId, @AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException;
}
