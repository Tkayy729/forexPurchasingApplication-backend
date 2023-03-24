package com.secondstax.fxPurchasingApplication.controller;

import com.secondstax.fxPurchasingApplication.dto.AuthenticationRequest;
import com.secondstax.fxPurchasingApplication.dto.AuthenticationResponse;
import com.secondstax.fxPurchasingApplication.dto.RegisterRequest;
import com.secondstax.fxPurchasingApplication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}