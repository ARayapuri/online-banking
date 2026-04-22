package org.bank.feign;

import org.bank.dto.AccountCreateRequest;
import org.bank.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "account-service")
public interface AccountClient {

    //@PostMapping("/api/accounts/create")
    @PostMapping("/api/accounts")
    AccountResponse createAccount(@RequestBody AccountCreateRequest request);

    /*@PostMapping("/api/accounts/interal-create")
    AccountResponse createAccount(@RequestBody AccountCreateRequest request);*/

}
