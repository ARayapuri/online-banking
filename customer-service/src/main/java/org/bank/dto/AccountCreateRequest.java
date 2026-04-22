package org.bank.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountCreateRequest {

    private String customerId;
    private String accountHolderName;
    private BigDecimal initialBalance;
}
