package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.RegisterRequest;
import com.secondstax.fxPurchasingApplication.exception.TraderAlreadyExistException;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.TraderRepository;
import com.secondstax.fxPurchasingApplication.service.AuthenticationService;
import com.secondstax.fxPurchasingApplication.service.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationServiceImplTest {
    final Trader trader = Trader.builder().email("test@gmail.com").build();
    @Autowired
    private AuthenticationService serviceUnderTest;
    @MockBean
    private TraderRepository repository;
    @MockBean
    private JwtService jwtService;

    @Test
    void testThat_registeringAnExistingTrader_shouldThrowError() {
        doReturn(Optional.of(trader)).when(repository).findByEmail("test@gmail.com");

        Assertions.assertThrows(TraderAlreadyExistException.class, () -> serviceUnderTest.register(RegisterRequest.builder().email("test@gmail.com").build()));
    }

    @Test
    void testThat_registeringAnNewTrader_shouldThrowError() {
        doReturn(Optional.of(trader)).when(repository).findByEmail("test@gmail.com");

        Assertions.assertThrows(TraderAlreadyExistException.class, () -> serviceUnderTest.register(RegisterRequest.builder().email("test@gmail.com").build()));

    }

    @Test
    void testThat_registeringAnNewTrader_shouldCreateTrader() {
        doReturn(Optional.empty()).when(repository).findByEmail("test@gmail.com");
        serviceUnderTest.register(RegisterRequest.builder().password("fdmksf").email("test@gmail.com").build());
        verify(repository, times(1)).save(any());
        verify(jwtService, times(1)).generateToken(any());

    }

}