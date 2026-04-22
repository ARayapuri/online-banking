package org.bank.account.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {

    private String accountNumber;
    private String customerId;
    private String accountHolderName;
    private BigDecimal balance;
    private String message;
}
