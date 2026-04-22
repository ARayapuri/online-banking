package org.bank.account.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountCreateRequest {

    @NotBlank
    private String customerId;

    @NotBlank
    private String accountHolderName;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal initialBalance;

    //private LocalDateTime createdAt; // now added
}
