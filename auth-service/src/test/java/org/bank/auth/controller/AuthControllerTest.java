package org.bank.auth.controller;

import org.bank.auth.dto.LoginRequest;

import org.bank.auth.dto.LoginResponse;

import org.bank.auth.service.AuthService;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;
    private LoginRequest loginRequest;
    private LoginResponse loginResponse;

    @BeforeEach
    void setUp() {

        loginRequest = new LoginRequest();
        loginRequest.setEmail("ashok@gmail.com");
        loginRequest.setPassword("1234");
        loginResponse = LoginResponse.builder()
                .customerId("CUST-1001")
                .fullName("Ashok Kumar")
                .message("Login successful")
                .build();

    }

   /* @Test
    void testLogin_Success() {

        when(authService.login(loginRequest))
                .thenReturn(loginResponse);

        ResponseEntity<LoginResponse> response =
                authController.login(loginRequest);
        assertNotNull(response);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("CUST-1001", response.getBody().getCustomerId());
        assertEquals("Ashok Kumar", response.getBody().getFullName());
        assertEquals("Login successful", response.getBody().getMessage());

        verify(authService, times(1))
                .login(loginRequest);

    }*/

   /* @Test
    void testLogin_Exception() {

        when(authService.login(loginRequest))
                .thenThrow(new RuntimeException("Invalid credentials"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authController.login(loginRequest)
        );

        assertEquals("Invalid credentials", exception.getMessage());

        verify(authService, times(1))
                .login(loginRequest);

    }*/

}

