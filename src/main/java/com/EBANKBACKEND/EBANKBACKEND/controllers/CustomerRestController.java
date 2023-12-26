package com.EBANKBACKEND.EBANKBACKEND.controllers;

import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.CustomerNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_user')")
    public List<CustomerDto> getListCustomers() {
        return bankAccountService.listCustomer();
    }
    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_user')")
    public CustomerDto getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }
    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_admin')")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        return  bankAccountService.saveCustomer(customerDto);
    }

    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_admin')")
    public CustomerDto updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDto customerDto) {
        customerDto.setId(customerId);
        return bankAccountService.updateCustomer(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_admin')")
    public void  deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_user')")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
}
