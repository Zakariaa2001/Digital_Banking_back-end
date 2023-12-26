package com.EBANKBACKEND.EBANKBACKEND;

import com.EBANKBACKEND.EBANKBACKEND.dtos.BankAccountDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.CurrentBankAccountDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.CustomerDto;
import com.EBANKBACKEND.EBANKBACKEND.dtos.SavingBankAccountDto;
import com.EBANKBACKEND.EBANKBACKEND.entities.*;
import com.EBANKBACKEND.EBANKBACKEND.enums.AccountStatus;
import com.EBANKBACKEND.EBANKBACKEND.enums.OperationType;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BalanceNotSufficientException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.BankAccountNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.exceptions.CustomerNotFoundException;
import com.EBANKBACKEND.EBANKBACKEND.repositories.AccountOperationRepository;
import com.EBANKBACKEND.EBANKBACKEND.repositories.BankAccountRepository;
import com.EBANKBACKEND.EBANKBACKEND.repositories.CustomerRepository;
import com.EBANKBACKEND.EBANKBACKEND.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@SpringBootApplication
public class EBankBackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EBankBackendApplication.class);
		app.setAllowCircularReferences(true);
		app.run(args);
	}


}
