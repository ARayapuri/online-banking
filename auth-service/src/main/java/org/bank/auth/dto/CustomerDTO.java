package org.bank.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerDTO {

    private String customerId;
    private String fullName;
    private String email;
    private String mobileNo; //mobile
    private String password;
    private String status;
}
