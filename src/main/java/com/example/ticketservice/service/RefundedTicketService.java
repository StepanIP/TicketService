package com.example.ticketservice.service;

import com.example.ticketservice.model.BusRoute;
import com.example.ticketservice.model.RefundedTicket;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RefundedTicketService {

    RefundedTicket create(RefundedTicket user);
    RefundedTicket readById(long id);
    RefundedTicket update(RefundedTicket user);
    void delete(long id);
    List<RefundedTicket> getAll();

}
