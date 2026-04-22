package org.bank.auth.feign;

import org.bank.auth.dto.CustomerAuthDTO;
import org.bank.auth.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/api/customers")

    CustomerDTO getByEmail(@RequestParam String email);

    @GetMapping("/api/customers/internal")
    CustomerAuthDTO getCustomerAuthDto(@RequestParam String email);
}
