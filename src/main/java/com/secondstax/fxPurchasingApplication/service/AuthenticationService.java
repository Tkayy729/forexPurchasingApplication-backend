package com.secondstax.fxPurchasingApplication.service;

import com.secondstax.fxPurchasingApplication.dto.AuthenticationRequest;
import com.secondstax.fxPurchasingApplication.dto.AuthenticationResponse;
import com.secondstax.fxPurchasingApplication.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse register(RegisterRequest request);
}
