package org.bank.service;

import org.bank.dto.CustomerAuthDTO;
import org.bank.dto.CustomerDTO;
import org.bank.dto.CustomerRequest;
import org.bank.dto.CustomerResponse;

public interface CustomerService {

    CustomerResponse registerCustomer(CustomerRequest customerRequest);

    CustomerDTO getByEmail(String email);

    CustomerAuthDTO getCustomerForAuth(String email);
}
