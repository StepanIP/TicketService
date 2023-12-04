package com.example.ticketservice.controller;

import com.example.ticketservice.TicketServiceApplication;
import com.example.ticketservice.dto.request.SignUpRequest;
import com.example.ticketservice.dto.request.SigninRequest;
import com.example.ticketservice.dto.response.JwtAuthenticationResponse;
import com.example.ticketservice.service.security.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes= TicketServiceApplication.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void testSignup() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest("Username", "Lastname", "test@gmail.com", "password");

        JwtAuthenticationResponse response = new JwtAuthenticationResponse("token");

        when(authenticationService.signup(signUpRequest)).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(signUpRequest)))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).signup(signUpRequest);
    }

    @Test
    void testSignin() throws Exception {
        SigninRequest signinRequest = new SigninRequest("username", "password");

        JwtAuthenticationResponse response = new JwtAuthenticationResponse("token");

        when(authenticationService.signin(signinRequest)).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(signinRequest)))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).signin(signinRequest);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}