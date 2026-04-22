package org.bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {

    private String accountNumber;
    private String customerId;
    private String accountHolderName;
    private BigDecimal balance;
    private String message;
}
