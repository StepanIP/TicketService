package com.example.ticketservice.service;

import com.example.ticketservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    List<User> getAll();
    User readByEmail(String email);
    UserDetailsService userDetailsService();

}
