package com.secondstax.fxPurchasingApplication.repository;

import com.secondstax.fxPurchasingApplication.model.ProviderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<ProviderData, Long> {
}
