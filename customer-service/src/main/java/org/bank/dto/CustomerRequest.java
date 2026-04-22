package org.bank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String mobileNo;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
