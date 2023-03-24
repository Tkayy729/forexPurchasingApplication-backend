package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.AuthenticationRequest;
import com.secondstax.fxPurchasingApplication.dto.RegisterRequest;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.exception.TraderAlreadyExistException;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.TokenRepository;
import com.secondstax.fxPurchasingApplication.repository.TraderRepository;
import com.secondstax.fxPurchasingApplication.service.AuthenticationService;
import com.secondstax.fxPurchasingApplication.service.JwtService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationServiceImplTest {
    @Autowired
    private AuthenticationService serviceUnderTest;
    @MockBean
    private TraderRepository repository;
    @MockBean
    private TokenRepository tokenRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private AuthenticationManager authenticationManager;

    final Trader trader = Trader.builder().email("test@gmail.com").build();


    @Test
    void testThat_registeringAnExistingTrader_shouldThrowError() {
        doReturn(Optional.of(trader)).when(repository).findByEmail("test@gmail.com");

        Assertions.assertThrows(TraderAlreadyExistException.class, () -> serviceUnderTest.register(RegisterRequest.builder().email("test@gmail.com").build()));
    }

    @Test
    void testThat_registeringAnNewTrader_shouldCreateATrader() {
        doReturn(Optional.of(trader)).when(repository).findByEmail("test@gmail.com");
        serviceUnderTest.register(RegisterRequest.builder().email("test@gmail.com").build());
        verify(repository,times(1)).save(any());
        verify(jwtService,times(1)).generateToken(any());
    }

}