package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;
import com.secondstax.fxPurchasingApplication.dto.OrderResponse;
import com.secondstax.fxPurchasingApplication.enums.OrderStatus;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import com.secondstax.fxPurchasingApplication.exception.OrderCreationConflictException;
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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        BankAccount bankAccount = bankAccountService.findAccount(orderRequest.bankAccountId() ,userDetails);
        if (!Objects.equals(orderRequest.exchange(), bankAccount.getCurrency())) {
            throw new OrderCreationConflictException("Cannot create order because selected account cannot receive this currency");
        }

        Order newOrder = Order.builder().exchange(orderRequest.exchange()).
                trader(trader)
                .bankAccount(bankAccount)
                .provider(Provider.valueOf(orderRequest.provider().toString()))
                .amount(orderRequest.amount())
                .build();

        orderValidation( orderRequest,  newOrder);

        var savedOrder = repository.save(newOrder);
        return new OrderResponse(savedOrder.getId(),savedOrder.getTrader().getEmail(), savedOrder.getProvider(), savedOrder.getBankAccount(), savedOrder.getExchange(), savedOrder.getAmount(), savedOrder.getStatus());
    }

    private void orderValidation(OrderRequest orderRequest, Order newOrder){
        if(orderRequest.amount() <= 0){
            throw new OrderCreationConflictException("Cannot create an order with less or zero amount");
        } else if (orderRequest.amount() >= 1 && orderRequest.amount() <= 50) {
            newOrder.setStatus(OrderStatus.FULFILLED);
        }else if (orderRequest.amount() >= 51 && orderRequest.amount() <= 100) {
            newOrder.setStatus(OrderStatus.PENDING);
        } else {
            newOrder.setStatus(OrderStatus.FAILED);
        }
    }

    private Order getOrder(Long orderId,@AuthenticationPrincipal UserDetails userDetails) throws ResourceNotFoundException {
        Optional<Order> order = repository.findById(orderId);
        if(order.isPresent() && !Objects.equals(order.get().getTrader().getEmail(), userDetails.getUsername())){
            throw new ResourceNotFoundException("Order not found");
        }
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order not found");
        }
        return order.get();
    }

    public OrderResponse getOne(Long orderId,@AuthenticationPrincipal UserDetails userDetails ) throws ResourceNotFoundException {
        var foundOrder = getOrder(orderId,userDetails);
        return new OrderResponse(foundOrder.getId(), foundOrder.getTrader().getEmail(), foundOrder.getProvider(), foundOrder.getBankAccount(), foundOrder.getExchange(), foundOrder.getAmount(), foundOrder.getStatus());
    }

    public List<OrderResponse> getAllOrdersOfTrader(UserDetails userDetails) throws ResourceNotFoundException {
        String email = userDetails.getUsername();
        Trader trader = traderService.getTrader(email);
        Optional<List<Order>> allOrders = repository.findByTrader(trader);
        if (allOrders.isEmpty()) {
            throw new ResourceNotFoundException("Empty List of orders");
        }
        var orders = allOrders.get();
        return orders.parallelStream().map(order -> new OrderResponse(order.getId(),order.getTrader().getEmail(), order.getProvider(), order.getBankAccount(), order.getExchange(), order.getAmount(),order.getStatus())).collect(Collectors.toList());
    }


}
