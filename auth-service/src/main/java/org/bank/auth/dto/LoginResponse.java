package org.bank.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

    private String customerId;
    private String fullName;
    private String message;
    private String token;
}
