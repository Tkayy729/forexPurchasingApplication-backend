package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.dto.BankAccountRequest;
import com.secondstax.fxPurchasingApplication.dto.BankAccountResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface BankAccountService {
    BankAccountResponse addBankAccount(BankAccountRequest bankAccountRequest, UserDetails userDetails);

    List<BankAccountResponse> getAllBankAccountOfTrader(UserDetails userDetails) throws ResourceNotFoundException;
    BankAccountResponse getAccount(Long bankAccountId) throws ResourceNotFoundException;

    BankAccount findAccount(Long bankAccountId) throws ResourceNotFoundException;
}
