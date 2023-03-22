package com.secondstax.fxPurchasingApplication.repository;

import com.secondstax.fxPurchasingApplication.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
