package org.bank.auth.serviceimpl;

import org.bank.auth.dto.CustomerAuthDTO;
import org.bank.auth.dto.CustomerDTO;
import org.bank.auth.dto.LoginRequest;
import org.bank.auth.dto.LoginResponse;
import org.bank.auth.feign.CustomerClient;
import org.bank.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private CustomerClient customerClient;

    @Override
    public LoginResponse login(LoginRequest request) {

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        CustomerAuthDTO customerAuthDTO = customerClient.getCustomerAuthDto(request.getEmail());

        if (customerAuthDTO == null) {
            throw new RuntimeException("User not found");
        }

        if (!customerAuthDTO.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!"ACTIVE".equalsIgnoreCase(customerAuthDTO.getStatus())) {
            throw new RuntimeException("User account is not active");
        }
        return LoginResponse.builder()
                .customerId(customerAuthDTO.getCustomerId())
                .fullName(customerAuthDTO.getFullName())
                .message("Login successful")
                .build();
    }

}

