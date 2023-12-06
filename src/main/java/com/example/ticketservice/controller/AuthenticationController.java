package com.example.ticketservice.controller;

import com.example.ticketservice.dto.request.SignUpRequest;
import com.example.ticketservice.dto.request.SigninRequest;
import com.example.ticketservice.dto.response.JwtAuthenticationResponse;
import com.example.ticketservice.service.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:3030")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    @CrossOrigin(origins = "http://localhost:3030")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}