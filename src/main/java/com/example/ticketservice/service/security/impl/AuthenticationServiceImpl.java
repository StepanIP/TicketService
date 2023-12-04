package com.example.ticketservice.service.security.impl;

import com.example.ticketservice.dto.request.SignUpRequest;
import com.example.ticketservice.dto.request.SigninRequest;
import com.example.ticketservice.dto.response.JwtAuthenticationResponse;
import com.example.ticketservice.model.Role;
import com.example.ticketservice.model.User;
import com.example.ticketservice.repository.UserRepository;
import com.example.ticketservice.service.security.AuthenticationService;
import com.example.ticketservice.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User account = User.builder()
                .firstName(request.getName())
                .lastName(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(new Role("USER"))
                .build();
        userRepository.save(account);
        var jwt = jwtService.generateToken(account);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}