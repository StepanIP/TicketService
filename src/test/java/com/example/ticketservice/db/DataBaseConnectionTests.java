package com.example.ticketservice.db;

import com.example.ticketservice.model.User;
import com.example.ticketservice.repository.RoleRepository;
import com.example.ticketservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataBaseConnectionTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @Transactional
    void testCreateUser(){
        User user = new User();
        user.setFirstName("Stepan");
        user.setLastName("Skrypnychuk");
        user.setEmail("nyGmail@gmail.com");
        user.setPassword("somePASS12&*");
        user.setRole(roleRepository.getReferenceById(1L));
        int beforeSize = userRepository.findAll().size();

        userRepository.save(user);
        User actual = userRepository.findByEmail(user.getEmail());

        assertEquals(user.getFirstName(), actual.getFirstName());
        assertEquals(user.getLastName(), actual.getLastName());
        assertEquals(user.getEmail(), actual.getEmail());
        assertNotEquals(beforeSize, userRepository.findAll().size());
    }
}
