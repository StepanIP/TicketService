package com.example.ticketservice.controller;

import com.example.ticketservice.model.User;
import com.example.ticketservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/Rocket/account")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService AppointmentService) {
        this.userService = AppointmentService;
    }

    @GetMapping
    public ResponseEntity<User> getPublicData(Principal principal) {
        User person = userService.readByEmail(principal.getName());
        return new ResponseEntity<>(User.builder().
                firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .password("*".repeat(person.getPassword().length()))
                .build(), HttpStatus.OK);
    }
}
