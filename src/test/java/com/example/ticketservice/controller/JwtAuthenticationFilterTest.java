package com.example.ticketservice.controller;

import com.example.ticketservice.TicketServiceApplication;
import com.example.ticketservice.configuration.JwtAuthenticationFilter;
import com.example.ticketservice.service.UserService;
import com.example.ticketservice.service.security.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TicketServiceApplication.class)
@Transactional
@AutoConfigureMockMvc
public class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private String validToken = "valid-jwt-token";

    @Test
    public void testDoFilterInternal_NoAuthorizationHeader() throws IOException, jakarta.servlet.ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testDoFilterInternal_InvalidAuthorizationHeader() throws IOException, jakarta.servlet.ServletException {
        MockHttpServletRequest request = Mockito.mock(MockHttpServletRequest.class);
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

}