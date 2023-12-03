package com.EBANKBACKEND.EBANKBACKEND.controllers;

import com.EBANKBACKEND.EBANKBACKEND.dtos.*;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BalanceNotSufficientException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BankAccountNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDto getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDto> ListAccounts() {
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDto> getHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDto getAccountHistory(@PathVariable String accountId,
                                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                                     @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/debit")
    public DebitDto debit(@RequestBody DebitDto debitDto) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDto.getAccounId(),debitDto.getAmount(), debitDto.getDescription());
        return debitDto;
    }
    @PostMapping("/accounts/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.credit(creditDto.getAccounId(),creditDto.getAmount(), creditDto.getDescription());
        return creditDto;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDto transferRequestDto) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(transferRequestDto.getAccountSource()
                ,transferRequestDto.getAccountDestination()
                ,transferRequestDto.getAmount()
                );
    }

}
