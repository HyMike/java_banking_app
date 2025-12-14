package com.banking.banking_app.service;

import com.banking.banking_app.dto.AccountDTO;
import com.banking.banking_app.enums.AccountType;
import com.banking.banking_app.enums.TransactionType;
import com.banking.banking_app.exception.InsufficientFundsException;
import com.banking.banking_app.exception.ResourceNotFoundException;
import com.banking.banking_app.model.Account;
import com.banking.banking_app.model.Transaction;
import com.banking.banking_app.model.User;
import com.banking.banking_app.repository.AccountRepository;
import com.banking.banking_app.repository.TransactionRepository;
import com.banking.banking_app.repository.UserRepository;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import javax.naming.InsufficientResourcesException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final UserRepository userRepository; 

    public AccountService(AccountRepository accountRepo, TransactionRepository transactionRepo, UserRepository userRepository){
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
        this.userRepository = userRepository;

    }

    public Account findAccount(Long id){
        return accountRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Account not found"));
    }

    public void saveTransaction(Account account, double amount, TransactionType type){
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setAccount(account);
        tx.setType(type);
        tx.setTimeStamp(LocalDateTime.now());

        transactionRepo.save(tx);

    }

    @Transactional
    public Account deposit(Long accountId, double amount){
        Account account = findAccount(accountId);

        account.setBalance(account.getBalance() + amount);
        saveTransaction(account,amount,TransactionType.DEPOSIT);
        return account;

    }

    @Transactional
    public Account withdrawal(Long id, double amount){
        Account account = findAccount(id);

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Not Enought Funds");
        } else {
            double balance = account.getBalance();
            balance -= amount;
            account.setBalance(balance);
            saveTransaction(account,balance, TransactionType.WITHDRAWAL);
            return account;
        }

    }

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {
        withdrawal(fromId, amount);
        deposit(toId, amount);
    }

    @Transactional
    public AccountDTO createAccount(AccountType type, double balance, Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Account account = new Account();
        account.setBalance(balance);
        account.setType(type);
        account.setUser(user);


        Account accountSaved = accountRepo.save(account);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(accountSaved.getBalance());
        accountDTO.setType(accountSaved.getType());
        accountDTO.setUserId(accountSaved.getUser().getId());
        return accountDTO;

    }

}
