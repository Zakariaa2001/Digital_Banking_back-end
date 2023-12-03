package com.EBANKBACKEND.EBANKBACKEND.dtos;

import lombok.Data;

@Data
public class CreditDto {
    private String accounId;
    private double amount;
    private String description;
}
