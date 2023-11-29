package com.EBANKBACKEND.EBANKBACKEND.dtos;

import com.EBANKBACKEND.EBANKBACKEND.entities.BankAccount;
import com.EBANKBACKEND.EBANKBACKEND.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class AccountOperationDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;


}
