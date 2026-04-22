package org.bank.controller;

import jakarta.validation.Valid;
import org.bank.common.ApiResponse;
import org.bank.dto.CustomerAuthDTO;
import org.bank.dto.CustomerDTO;
import org.bank.dto.CustomerRequest;
import org.bank.dto.CustomerResponse;
import org.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/apigatwayport")
    public String getByPort() {
        return "Response from port: " + port;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse customer = customerService.registerCustomer(request);

        ApiResponse<CustomerResponse> response = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Customer created successfully")
                .data(customer)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> getByEmail( @Valid
            @RequestParam String email) {

        CustomerDTO customer = customerService.getByEmail(email);

        ApiResponse<CustomerDTO> response = ApiResponse.<CustomerDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Customer fetched successfully")
                .data(customer)
                .build();

        return ResponseEntity.ok(response);
    }

    /*@GetMapping("/internal")
    public ResponseEntity<ApiResponse<CustomerAuthDTO>> getCustomerForAuth(@Valid @RequestParam String email){

        CustomerAuthDTO customer = customerService.getCustomerForAuth(email);
        ApiResponse<CustomerAuthDTO> response = ApiResponse.<CustomerAuthDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Customer fetched successfully")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }*/
    @GetMapping("/internal")
    public CustomerAuthDTO getCustomerForAuth(@RequestParam String email){
        return customerService.getCustomerForAuth(email);
    }
}
