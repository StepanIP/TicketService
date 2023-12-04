package com.example.ticketservice.service;

import com.example.ticketservice.model.Role;
import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TicketService {

    Ticket create(Ticket user);
    Ticket readById(long id);
    Ticket update(Ticket user);
    void delete(long id);
    List<Ticket> getAll();

    List<Ticket> findTicketByUser(User user);

}
