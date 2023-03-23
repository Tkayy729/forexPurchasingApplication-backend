package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.BankAccountRequest;
import com.secondstax.fxPurchasingApplication.dto.BankAccountResponse;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.BankAccountRepository;
import com.secondstax.fxPurchasingApplication.service.BankAccountService;
import com.secondstax.fxPurchasingApplication.service.TraderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final TraderService traderService;

    @Override
    public BankAccountResponse addBankAccount(BankAccountRequest bankAccountRequest, UserDetails userDetails) {
        String email = userDetails.getUsername();
        Trader trader = traderService.getTrader(email);
        BankAccount newAccount = BankAccount.builder().trader(trader).currency(bankAccountRequest.getCurrency()).name(bankAccountRequest.getName()).accountNumber(bankAccountRequest.getAccountNumber()).branchName(bankAccountRequest.getBranchName()).build();
        var savedAccount = bankAccountRepository.save(newAccount);
        return BankAccountResponse.builder().id(savedAccount.getId()).name(savedAccount.getName()).currency(savedAccount.getCurrency()).branchName(savedAccount.getBranchName()).accountNumber(savedAccount.getAccountNumber()).build();
    }

    public List<BankAccountResponse> getAllBankAccountOfTrader(UserDetails userDetails) throws ResourceNotFoundException {
        String email = userDetails.getUsername();
        Trader trader = traderService.getTrader(email);
        Optional<List<BankAccount>> allAccounts = bankAccountRepository.findByTrader(trader);
        if (allAccounts.isEmpty()) {
            throw new ResourceNotFoundException("Empty List of accounts");
        }
        var accounts = allAccounts.get();
        return accounts.parallelStream().map(account -> BankAccountResponse.builder().accountNumber(account.getAccountNumber()).branchName(account.getBranchName()).id(account.getId()).name(account.getName()).currency(account.getCurrency()).build()).collect(Collectors.toList());
    }

    public BankAccountResponse getAccount(Long bankAccountId) throws ResourceNotFoundException {
        var foundAccount = findAccount(bankAccountId);
        return BankAccountResponse.builder().id(foundAccount.getId()).branchName(foundAccount.getBranchName()).name(foundAccount.getName()).currency(foundAccount.getCurrency()).accountNumber(foundAccount.getAccountNumber()).build();
    }

    public BankAccount findAccount(Long bankAccountId) throws ResourceNotFoundException {
        Optional<BankAccount> account = bankAccountRepository.findById(bankAccountId);
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("Account not found");
        }
        return account.get();
    }


}
