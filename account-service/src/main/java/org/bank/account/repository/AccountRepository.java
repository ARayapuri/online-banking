package org.bank.account.repository;

import jakarta.validation.constraints.NotBlank;
import org.bank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByCustomerId(String customerId);

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByCustomerId(@NotBlank String customerId);
}
