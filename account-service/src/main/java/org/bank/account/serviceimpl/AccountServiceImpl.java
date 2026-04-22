package org.bank.account.serviceimpl;

import org.bank.account.dto.AccountCreateRequest;
import org.bank.account.dto.AccountResponse;
import org.bank.account.entity.Account;
import org.bank.account.exception.DuplicateResouceException;
import org.bank.account.exception.ResourceNotFoundException;
import org.bank.account.repository.AccountRepository;
import org.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository repository;

   /* @Override
    public AccountResponse createAccount(AccountCreateRequest request) {
        if (repository.existsByCustomerId(request.getCustomerId())) {
            throw new RuntimeException("Account already exists for this customer");
        }

        Account account = new Account();
        account.setAccountNumber("ACC-" + UUID.randomUUID().toString().substring(0,8));
        account.setCustomerId(request.getCustomerId());
        account.setAccountHolderName(request.getAccountHolderName());
        account.setBalance(request.getInitialBalance());
        account.setStatus("ACTIVE");

        repository.save(account);

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .message("Account created successfully")
                .build();
    }*/

    @Override
    public AccountResponse updateBalance(AccountCreateRequest request) {
        Account account = repository
                .findByCustomerId(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if(request.getInitialBalance().compareTo(BigDecimal.ZERO)<0){
            throw new RuntimeException("Amount must be positive");
        }

        account.setBalance(
                account.getBalance().add(request.getInitialBalance())
        );

        repository.save(account);

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .message("Amount updated successfully")
                .build();

    }

    @Override
    public AccountResponse getAccountByAccountNumber(String accountNumber) {

        Account account = repository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .accountHolderName(account.getAccountHolderName())
                .balance(account.getBalance())
                .message("Account fetched successfully")
                .build();
    }


}

