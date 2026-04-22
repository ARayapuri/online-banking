package org.bank.serviceimpl;

import jakarta.transaction.Transactional;
import org.bank.dto.*;
import org.bank.entity.Customer;
import org.bank.exception.CustomException;
import org.bank.exception.CustomerNotFoundException;
import org.bank.exception.DuplicateResourceException;
import org.bank.feign.AccountClient;
import org.bank.repository.CustomerRepository;
import org.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountClient accountClient;

    @Override
    @Transactional
    public CustomerResponse registerCustomer(CustomerRequest request) {

        //duplicate checks
        if (customerRepository.findByMobileNo(request.getMobileNo()).isPresent()) {
            throw new DuplicateResourceException("Mobile number already exists");
        }

        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        Customer customer = new Customer();
        customer.setCustomerId("CUST-" + UUID.randomUUID().toString().substring(0,8));
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setMobileNo(request.getMobileNo());
        customer.setPassword(request.getPassword());
        customer.setStatus("ACTIVE");

        customerRepository.save(customer);

        // 🔥 Create Account Automatically
        AccountCreateRequest accountRequest = new AccountCreateRequest();
        accountRequest.setCustomerId(customer.getCustomerId());
        accountRequest.setAccountHolderName(customer.getFullName());
        accountRequest.setInitialBalance(BigDecimal.ZERO);

        AccountResponse accountResponse =
                accountClient.createAccount(accountRequest);

        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .fullName(customer.getFullName())
                .mobileNo(customer.getMobileNo())
                .email(customer.getEmail())
                .message("Customer created successfully")
                .build();
    }


    @Override
    public CustomerDTO getByEmail(String email) {

        Customer customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Invalid email"));

        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setMobileNo(customer.getMobileNo());
       // dto.setPassword(customer.getPassword());
        dto.setStatus(customer.getStatus());

        return dto;
    }

    @Override
    public CustomerAuthDTO getCustomerForAuth(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("User not found"));

        CustomerAuthDTO dto = new CustomerAuthDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setStatus(customer.getStatus());

        return dto;
    }

}
