package com.secondstax.fxPurchasingApplication.repository;

import com.secondstax.fxPurchasingApplication.model.BankAccount;
import com.secondstax.fxPurchasingApplication.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<List<BankAccount>> findByTrader(Trader trader);
}
