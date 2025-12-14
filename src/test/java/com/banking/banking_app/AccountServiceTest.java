package com.banking.banking_app;

import com.banking.banking_app.model.Account;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.repository.TransactionRepository;
import com.banking.banking_app.service.AccountService;
import org.assertj.core.error.OptionalShouldContainInstanceOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void deposit_ShouldIncreaseTheBalance(){
        Account account = new Account();
        account.setBalance(100);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.deposit(1l, 50);
        assertEquals(150, account.getBalance());

    }
    @Test
    void withdrawal_ShouldDeductTheCorrectBalance() {
        Account account = new Account();
        account.setBalance(5);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.withdrawal(1L, 2);
        assertEquals(3, account.getBalance());

    }






}

