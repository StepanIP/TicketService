package com.example.ticketservice.controller;

import com.example.ticketservice.model.RefundedTicket;
import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.model.User;
import com.example.ticketservice.service.RefundedTicketService;
import com.example.ticketservice.service.TicketService;
import com.example.ticketservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Rocket/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final RefundedTicketService refundedTicketService;

    private final UserService userService;

    public TicketController(TicketService ticketService, RefundedTicketService refundedTicketService, UserService userService) {
        this.ticketService = ticketService;
        this.refundedTicketService = refundedTicketService;
        this.userService = userService;
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000/**")
    public ResponseEntity<List<Ticket>> getAll(Principal principal) {
        User user = userService.readByEmail(principal.getName());
        return new ResponseEntity<>(ticketService.findTicketByUser(user), HttpStatus.OK);
    }

    @PostMapping("/refund")
    @CrossOrigin(origins = "http://localhost:3000/**")
    public ResponseEntity<HttpStatus> refundTicket(Principal principal, Ticket ticket, String refundReason) {
        User user = userService.readByEmail(principal.getName());
        ticketService.delete(ticket.getId());
        refundedTicketService.create(RefundedTicket.builder()
                        .user(user)
                        .refundReason(refundReason)
                        .refundStatus("requested")
                        .refundDatetime(LocalDateTime.now())
                .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
