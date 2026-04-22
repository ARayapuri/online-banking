package org.bank.repository;

import org.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNo(String mobileNo);

    Optional<Customer> findByEmail(String email);
}
