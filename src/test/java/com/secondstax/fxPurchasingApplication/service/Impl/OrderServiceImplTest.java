package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.OrderRequest;

import com.secondstax.fxPurchasingApplication.exception.OrderCreationConflictException;
import com.secondstax.fxPurchasingApplication.exception.ResourceNotFoundException;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import com.secondstax.fxPurchasingApplication.model.Order;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.OrderRepository;
import com.secondstax.fxPurchasingApplication.service.BankAccountService;
import com.secondstax.fxPurchasingApplication.service.OrderService;
import com.secondstax.fxPurchasingApplication.service.TraderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static com.secondstax.fxPurchasingApplication.enums.Currency.EUR;
import static com.secondstax.fxPurchasingApplication.enums.Currency.NAIRA;
import static com.secondstax.fxPurchasingApplication.enums.Provider.PA;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService serviceUnderTest;

    @MockBean
    public  TraderService traderService;

    @MockBean
    public  BankAccountService bankAccountService;

    @MockBean
    public  OrderRepository repository;

    final Trader trader = Trader.builder().email("test@gmail.com").build();

    final OrderRequest orderRequest = new OrderRequest(PA,1L, EUR,50.0);
    final OrderRequest zeroAmountRequest = new OrderRequest(PA,1L, EUR,0.0);

    final BankAccount bankAccount = BankAccount.builder().trader(trader).id(1L).accountNumber("75421515").currency(EUR)
            .bankName("Ecobank").build();
    final BankAccount bankAccount1 = BankAccount.builder().trader(trader).id(1L).accountNumber("75421515").currency(NAIRA)
            .bankName("Ecobank").build();


    @Test
    void testThat_requestingForAnDifferentExchangeForBankAccount_shouldThrow409() throws ResourceNotFoundException {
       doReturn(bankAccount1).when(bankAccountService).findAccount(1L,trader);

        Assertions.assertThrows(OrderCreationConflictException.class, () -> serviceUnderTest.createOrder(orderRequest,trader));

    }

    @Test
    void testThat_placingOrderWithZeroOrLessAmount_shouldThrowError() throws ResourceNotFoundException {
        doReturn(bankAccount).when(bankAccountService).findAccount(1L,trader);

        Assertions.assertThrows(OrderCreationConflictException.class, () -> serviceUnderTest.createOrder(zeroAmountRequest,trader));

    }

    @Test
    void testThat_givenAValidRequest_shouldCreateOrderSuccessfully() throws ResourceNotFoundException {
        doReturn(bankAccount).when(bankAccountService).findAccount(1L,trader);
        doReturn(Order.builder().trader(trader).build()).when(repository).save(any());

        serviceUnderTest.createOrder(orderRequest,trader);

        verify(repository,times(1)).save(any());

    }


}