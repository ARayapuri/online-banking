package org.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    private String customerId;
    private String fullName;
    private String mobileNo;
    private String email;
    private String message;
    private String accountNumber;

}
