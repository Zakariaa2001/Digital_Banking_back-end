package com.EBANKBACKEND.EBANKBACKEND.repositories;

import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
