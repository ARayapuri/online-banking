package org.bank.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts",
        uniqueConstraints = @UniqueConstraint(name = "uq_account_number",columnNames = "account_number"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name ="customer_id", nullable = false)
    @NotBlank(message = "CustomerId is mandatory")
    private String customerId;

    @Column(name ="account_holder_name", nullable = false)
    @NotBlank(message = "Account holder name is mandatory")
    private String accountHolderName;

    @NotNull(message = "Initial balance is mandatory")
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal balance;

    private LocalDateTime createdAt;

    private String status;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }
}

