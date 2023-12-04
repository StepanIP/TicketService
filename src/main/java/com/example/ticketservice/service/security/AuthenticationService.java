package com.example.ticketservice.service.security;


import com.example.ticketservice.dto.request.SignUpRequest;
import com.example.ticketservice.dto.request.SigninRequest;
import com.example.ticketservice.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}