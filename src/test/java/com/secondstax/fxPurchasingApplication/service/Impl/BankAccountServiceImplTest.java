package com.secondstax.fxPurchasingApplication.service.Impl;

import com.secondstax.fxPurchasingApplication.dto.BankAccountRequest;
import com.secondstax.fxPurchasingApplication.model.BankAccount;
import com.secondstax.fxPurchasingApplication.model.Trader;
import com.secondstax.fxPurchasingApplication.repository.BankAccountRepository;
import com.secondstax.fxPurchasingApplication.service.BankAccountService;
import com.secondstax.fxPurchasingApplication.service.TraderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;



import static com.secondstax.fxPurchasingApplication.enums.Currency.EUR;
import static org.mockito.Mockito.*;

@SpringBootTest
class BankAccountServiceImplTest {
    final Trader trader = Trader.builder().email("test@gmail.com").build();

    final UserDetails userDetails = Trader.builder().email("test@gmail.com").build();
    final BankAccountRequest request = BankAccountRequest.builder().accountNumber("456").name("emmanuel").currency(EUR).bankName("Mile 7").build();

    final BankAccount bankAccount = BankAccount.builder().trader(trader).accountNumber("2324").accountNumber("456").name("emmanuel").currency(EUR).bankName("Mile 7").build();

    @Autowired
    private BankAccountService serviceUnderTest;
    @MockBean
    private BankAccountRepository bankAccountRepository;


    @MockBean
    private TraderService traderService;

    @Test
    void thatThat_whenGivenValidBankAccountRequest_accountShouldBeCreatedSuccessfully() {
        doReturn(trader).when(traderService).getTrader(userDetails.getUsername());
       doReturn(bankAccount).when(bankAccountRepository).save(any());

        serviceUnderTest.addBankAccount(request, userDetails);
        verify(bankAccountRepository, times(1)).save(any());

    }

}