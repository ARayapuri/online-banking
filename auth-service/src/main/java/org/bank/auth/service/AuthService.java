package org.bank.auth.service;

import org.bank.auth.dto.LoginRequest;
import org.bank.auth.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
