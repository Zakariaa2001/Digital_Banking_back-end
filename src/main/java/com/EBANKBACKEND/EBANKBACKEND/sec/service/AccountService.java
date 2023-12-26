package com.EBANKBACKEND.EBANKBACKEND.sec.service;

import com.EBANKBACKEND.EBANKBACKEND.sec.entities.AppRole;
import com.EBANKBACKEND.EBANKBACKEND.sec.entities.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;


public interface AccountService {

    AppUser addNewUser(String username, String password, String email, String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);


}
