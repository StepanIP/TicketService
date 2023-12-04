package com.example.ticketservice.service;


import com.example.ticketservice.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    Role create(Role user);
    Role readById(long id);
    Role update(Role user);
    void delete(long id);
    List<Role> getAll();

}
