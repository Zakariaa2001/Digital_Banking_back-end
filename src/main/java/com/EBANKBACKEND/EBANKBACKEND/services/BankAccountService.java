package com.EBANKBACKEND.EBANKBACKEND.services;

import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.BankAccount;
import com.EBANKBACKEND.EBANKBACKEND.entities.CurrentAccount;
import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import com.EBANKBACKEND.EBANKBACKEND.entities.SavingAccount;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BalanceNotSufficientException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BankAccountNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraf, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDto> listCustomer();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();

    CustomerDto getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomer(Long customerId);
}
