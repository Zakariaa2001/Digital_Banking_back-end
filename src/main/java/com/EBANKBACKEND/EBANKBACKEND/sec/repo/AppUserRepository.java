package com.EBANKBACKEND.EBANKBACKEND.sec.repo;

import com.EBANKBACKEND.EBANKBACKEND.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
