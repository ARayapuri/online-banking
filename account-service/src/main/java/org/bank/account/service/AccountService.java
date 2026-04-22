package org.bank.account.service;

import org.bank.account.dto.AccountCreateRequest;
import org.bank.account.dto.AccountResponse;

public interface AccountService {

   // AccountResponse createAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse getAccountByAccountNumber(String accountNumber);

    AccountResponse updateBalance(AccountCreateRequest request);
}
