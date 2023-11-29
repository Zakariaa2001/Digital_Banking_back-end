package com.EBANKBACKEND.EBANKBACKEND.mappers;

import com.EBANKBACKEND.EBANKBACKEND.dtos.AccountOperationDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.CurrentBankAccountDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.SavingBankAccountDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.AccountOperation;
import com.EBANKBACKEND.EBANKBACKEND.entities.CurrentAccount;
import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import com.EBANKBACKEND.EBANKBACKEND.entities.SavingAccount;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public CustomerDto fromCustomer(Customer customer) {
        CustomerDto customerDto=new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        //customerDto.setId(customer.getId());
        //customerDto.setName(customer.getName());
        //customerDto.setEmail(customer.getEmail());
        return customerDto;
    }
    public Customer fromCustomerDto(CustomerDto customerDto) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }
    public SavingBankAccountDto fromSavingBankAccount(SavingAccount savingAccount) {
            SavingBankAccountDto savingBankAccountDto= new SavingBankAccountDto();
            BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
            savingBankAccountDto.setCustomerDto(fromCustomer(savingAccount.getCustomer()));
            savingBankAccountDto.setType(savingAccount.getClass().getSimpleName());
            return savingBankAccountDto;
    }
    public SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto) {
            SavingAccount savingAccount= new SavingAccount();
            BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
            savingAccount.setCustomer(fromCustomerDto(savingBankAccountDto.getCustomerDto()));

            return savingAccount;

    }
    public CurrentBankAccountDto fromCurrentBankAccount(CurrentAccount currentAccount) {
        CurrentBankAccountDto currentBankAccountDto= new CurrentBankAccountDto();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
        currentBankAccountDto.setCustomerDto(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDto.setType(currentAccount.getClass().getSimpleName());

        return currentBankAccountDto;

    }
    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto) {
        CurrentAccount currentAccount= new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
        currentAccount.setCustomer(fromCustomerDto(currentBankAccountDto.getCustomerDto()));
        return currentAccount;
    }
    public AccountOperationDto fromAccountOperation(AccountOperation accountOperation) {
        AccountOperationDto accountOperationDto=new AccountOperationDto();
        BeanUtils.copyProperties(accountOperation,accountOperationDto);
        return  accountOperationDto;
    }
    public AccountOperation fromAccountOperationDto(AccountOperationDto accountOperationDto) {
        AccountOperation accountOperation=new AccountOperation();
        BeanUtils.copyProperties(accountOperationDto,accountOperation);
        return  accountOperation;
    }
}
