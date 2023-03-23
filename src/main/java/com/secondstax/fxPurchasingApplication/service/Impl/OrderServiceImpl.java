package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;
import com.secondstax.fxPurchasingApplication.dto.OrderResponse;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import com.secondstax.fxPurchasingApplication.model.Order;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.OrderRepository;
import com.secondstax.fxPurchasingApplication.service.BankAccountService;
import com.secondstax.fxPurchasingApplication.service.OrderService;
import com.secondstax.fxPurchasingApplication.service.TraderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    public final TraderService traderService;

    public final BankAccountService bankAccountService;

    public final OrderRepository repository;
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest, @AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
        String email = userDetails.getUsername();
        Trader trader = traderService.getTrader(email);
        BankAccount bankAccount = bankAccountService.findAccount(orderRequest.bankAccountId());
        if(!Objects.equals(orderRequest.exchange(), bankAccount.getCurrency())){
            throw new RuntimeException("Cannot create order because selected account cannot receive this currency");
        }
        Order newOrder = Order.builder().exchange(orderRequest.exchange()).
                trader(trader)
                .bankAccount(bankAccount)
                .provider(Provider.valueOf(orderRequest.provider().toString()))
                .amount(orderRequest.amount())
        .build();

        var savedOrder = repository.save(newOrder);
        return new OrderResponse(savedOrder.getTrader().getEmail(),savedOrder.getProvider(),savedOrder.getBankAccount().getId(),savedOrder.getExchange(),savedOrder.getAmount());
    }
}
