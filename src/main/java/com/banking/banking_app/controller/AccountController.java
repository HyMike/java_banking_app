package com.banking.banking_app.controller;

import com.banking.banking_app.dto.AccountDTO;
import com.banking.banking_app.dto.TransferRequestDTO;
import com.banking.banking_app.enums.AccountType;
import com.banking.banking_app.model.Account;
import com.banking.banking_app.service.AccountService;
import jakarta.persistence.EnumType;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;

    }

    @PostMapping("/{id}/deposit")
    public Account depositAccount(@PathVariable Long id, @RequestParam double amount){
        return accountService.deposit(id, amount);

    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody @Valid TransferRequestDTO request){
        accountService.transfer(
            request.getFromAccountId(),
            request.getToAccountId(),
            request.getAmount()
    );
    }

    @PostMapping("/create")
    public AccountDTO createAccount(@RequestBody @Valid AccountDTO request){
        return accountService.createAccount(
                request.getType(),
                request.getBalance(),
                request.getUserId()
        );
    }


}
