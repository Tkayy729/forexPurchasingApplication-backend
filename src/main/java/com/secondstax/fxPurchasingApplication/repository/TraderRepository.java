package com.secondstax.fxPurchasingApplication.repository;


import com.secondstax.fxPurchasingApplication.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraderRepository extends JpaRepository<Trader, String> {
    Optional<Trader> findByEmail(String email);
}
