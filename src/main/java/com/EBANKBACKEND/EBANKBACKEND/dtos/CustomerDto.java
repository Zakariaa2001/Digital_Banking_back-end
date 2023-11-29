package com.EBANKBACKEND.EBANKBACKEND.dtos;

import com.EBANKBACKEND.EBANKBACKEND.entities.BankAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class CustomerDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

}
