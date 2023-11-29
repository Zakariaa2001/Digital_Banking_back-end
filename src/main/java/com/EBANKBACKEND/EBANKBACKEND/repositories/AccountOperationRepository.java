package com.EBANKBACKEND.EBANKBACKEND.repositories;

import com.EBANKBACKEND.EBANKBACKEND.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
