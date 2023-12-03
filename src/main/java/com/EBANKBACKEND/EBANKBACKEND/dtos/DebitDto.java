package com.EBANKBACKEND.EBANKBACKEND.dtos;

import lombok.Data;

@Data
public class DebitDto {
    private String accounId;
    private double amount;
    private String description;
}
