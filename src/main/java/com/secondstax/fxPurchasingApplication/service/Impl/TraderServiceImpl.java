package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.exception.TraderNotFoundException;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.TraderRepository;
import com.secondstax.fxPurchasingApplication.service.TraderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraderServiceImpl implements TraderService {

    private final TraderRepository repository;

    @Override
    public Trader getTrader(String  email) {
        return repository.findByEmail(email).orElseThrow(() -> new TraderNotFoundException("Trader not found"));
    }

}
