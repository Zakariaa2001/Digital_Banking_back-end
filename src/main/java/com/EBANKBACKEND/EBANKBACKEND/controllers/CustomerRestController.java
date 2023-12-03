package com.EBANKBACKEND.EBANKBACKEND.controllers;

import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.CustomerNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDto> getListCustomers() {
        return bankAccountService.listCustomer();
    }
    @GetMapping("/customers/{id}")

    public CustomerDto getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }
    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        return  bankAccountService.saveCustomer(customerDto);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerDto updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDto customerDto) {
        customerDto.setId(customerId);
        return bankAccountService.updateCustomer(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    public void  deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/customers/search")
    public List<CustomerDto> searchCustomer(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
}
