package com.example.ticketservice.repository;

import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> getTicketByUser(User user);
}
