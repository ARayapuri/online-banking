package org.bank.auth.controller;

import jakarta.validation.Valid;
import org.bank.auth.common.ApiResponse;
import org.bank.auth.dto.LoginRequest;
import org.bank.auth.dto.LoginResponse;
import org.bank.auth.service.AuthService;
import org.bank.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${server.port}")
    private String port;

    @GetMapping("/apigatway")
    public String getByPort() {
        return "Response from port: " + port;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request){

        LoginResponse login = authService.login(request);

        //Generate JWT token using email
        String token = jwtUtil.generateToken(request.getEmail());

        login.setToken(token);

        ApiResponse<LoginResponse> response = ApiResponse.<LoginResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Login successfull")
                .data(login)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }
}
