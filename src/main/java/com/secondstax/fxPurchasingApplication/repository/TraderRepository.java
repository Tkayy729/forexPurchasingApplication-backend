package com.secondstax.fxPurchasingApplication.repository;


import com.secondstax.fxPurchasingApplication.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraderRepository extends JpaRepository<Trader, String> {
}
