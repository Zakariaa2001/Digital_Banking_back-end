package com.EBANKBACKEND.EBANKBACKEND.repositories;

import com.EBANKBACKEND.EBANKBACKEND.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM Customer c WHERE c.name like :kw")
    List<Customer> searchCustomers(@Param("kw") String keyword);
}
