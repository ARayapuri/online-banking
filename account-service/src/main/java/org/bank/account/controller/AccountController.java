package org.bank.account.controller;


import jakarta.validation.Valid;
import org.bank.account.dto.AccountCreateRequest;
import org.bank.account.dto.AccountResponse;
import org.bank.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @Value("${server.port}")
    private String port;

    @GetMapping("/apigatway")
    public String getByPort() {
        return "Response from port: " + port;
    }

    /*@PostMapping
    public ResponseEntity<AccountResponse> createAccount(
            @RequestBody AccountCreateRequest request) {

        AccountResponse response = service.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/

    @PostMapping("/addAmount")
    public ResponseEntity<AccountResponse> addBalance(@RequestBody AccountCreateRequest request){
        return ResponseEntity.ok(service.updateBalance(request));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(
            @PathVariable String accountNumber) {

        AccountResponse response =
                service.getAccountByAccountNumber(accountNumber);

        return ResponseEntity.ok(response);
    }


}


