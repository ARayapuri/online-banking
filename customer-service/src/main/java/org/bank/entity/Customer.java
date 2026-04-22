package org.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="customer",
       uniqueConstraints = {
        @UniqueConstraint(columnNames = "mobileNo"),
               @UniqueConstraint(columnNames = "email")
       })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="customer_id", unique = true, nullable = false)
    private String customerId;

    @NotBlank(message = "Customer name is mandatory")
    private String fullName;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNo;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    private LocalDate createdAt;

    private String status;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDate.now();
        this.status = "ACTIVE";
    }


}
