package com.EBANKBACKEND.EBANKBACKEND.dtos;

import com.EBANKBACKEND.EBANKBACKEND.enums.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data
public  class CurrentBankAccountDto extends BankAccountDto {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;
    private  double overDraft;


}
