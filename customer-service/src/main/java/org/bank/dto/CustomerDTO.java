package org.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerDTO {

    private String customerId;
    private String fullName;
    private String mobileNo;
    private String email;
    //private String password;
    private String status;
}
