package com.secondstax.fxPurchasingApplication.controller;

import com.secondstax.fxPurchasingApplication.dto.BankAccountRequest;
import com.secondstax.fxPurchasingApplication.dto.BankAccountResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bankaccounts")
@CrossOrigin("*")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @PostMapping("/")
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody BankAccountRequest bankAccountRequest, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.addBankAccount(bankAccountRequest, userDetails));

    }

    @GetMapping ("/")
    public ResponseEntity<List<BankAccountResponse>> getAllAccounts(@AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAllBankAccountOfTrader(userDetails));
    }

    @GetMapping ("/{bankAccountId}")
    public ResponseEntity<BankAccountResponse> getAccount(@PathVariable Long bankAccountId) throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(bankAccountService.getAccount(bankAccountId));
    }

}
