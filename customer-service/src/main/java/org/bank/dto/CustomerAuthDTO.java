package org.bank.dto;

import lombok.Data;

@Data
public class CustomerAuthDTO {

    private String customerId;
    private String fullName;
    private String email;
    private String password;
    private String status;
}
