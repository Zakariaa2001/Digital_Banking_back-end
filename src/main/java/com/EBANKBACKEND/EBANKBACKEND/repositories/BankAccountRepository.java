package com.EBANKBACKEND.EBANKBACKEND.repositories;

import com.EBANKBACKEND.EBANKBACKEND.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
