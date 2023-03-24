package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.AuthenticationRequest;
import com.secondstax.fxPurchasingApplication.dto.AuthenticationResponse;
import com.secondstax.fxPurchasingApplication.dto.RegisterRequest;
import com.secondstax.fxPurchasingApplication.enums.Role;
import com.secondstax.fxPurchasingApplication.enums.TokenType;
import com.secondstax.fxPurchasingApplication.exception.TraderAlreadyExistException;
import com.secondstax.fxPurchasingApplication.model.Token;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.TokenRepository;
import com.secondstax.fxPurchasingApplication.repository.TraderRepository;
import com.secondstax.fxPurchasingApplication.service.AuthenticationService;
import com.secondstax.fxPurchasingApplication.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TraderRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Trader> targetTrader = repository.findByEmail(request.getEmail());
        if(targetTrader.isPresent()){
            throw new TraderAlreadyExistException("This user already exist");
        }
        var trader = Trader.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        var savedTrader = repository.save(trader);
        var jwtToken = jwtService.generateToken(trader);
        saveTraderToken(savedTrader, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).message("success").build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var trader = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(trader);
        revokeAllUserTokens(trader);
        saveTraderToken(trader, jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).message("success").build();
    }

    private void saveTraderToken(Trader trader, String jwtToken) {
        var token = Token.builder().trader(trader).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Trader trader) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(trader.getEmail());
        validUserTokens.forEach(token -> System.out.println(token.getToken()));
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}

