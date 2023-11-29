package com.EBANKBACKEND.EBANKBACKEND.services;

import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.*;
import com.EBANKBACKEND.EBANKBACKEND.enums.OperationType;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BalanceNotSufficientException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BankAccountNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.CustomerNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.mappers.BankAccountMapperImpl;
import com.EBANKBACKEND.EBANKBACKEND.repositories.AccountOperationRepository;
import com.EBANKBACKEND.EBANKBACKEND.repositories.BankAccountRepository;
import com.EBANKBACKEND.EBANKBACKEND.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor //Lombok
@Slf4j  //Lombok for using logger :log.info("message")
public class BankAccountServiceImpl implements BankAccountService{

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtoMapper;
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("Saving new customer");
        Customer customer = dtoMapper.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraf, Long customerId) throws CustomerNotFoundException {
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if (customer== null)
            throw new CustomerNotFoundException("Customer not found");
        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraf);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
        return savedBankAccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer= customerRepository.findById(customerId).orElse(null);
        if (customer== null)
            throw new CustomerNotFoundException("Customer not found");
        SavingAccount savingAccount = new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
        return savedBankAccount;
    }


    @Override
    public List<CustomerDto> listCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = customers.stream().map(customer -> dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccountNotFoundException("BankAccount Not Found"));
        return bankAccount;
    }



    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount= getBankAccount(accountId);
        if (bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {

        BankAccount bankAccount= getBankAccount(accountId);
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"Transfer to"+accountIdDestination);
        credit(accountIdDestination,amount,"Tranfer from"+accountIdSource);
    }

    @Override
    public List<BankAccount> bankAccountList() {
        return bankAccountRepository.findAll();
    }

    @Override
    public CustomerDto getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                                                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        return dtoMapper.fromCustomer(customer);
    }
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        log.info("Saving new customer");
        Customer customer = dtoMapper.fromCustomerDto(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public  void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
