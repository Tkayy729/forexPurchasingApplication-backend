package com.secondstax.fxPurchasingApplication.repository;

import com.secondstax.fxPurchasingApplication.model.Order;
import com.secondstax.fxPurchasingApplication.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByTrader(Trader trader);
}
