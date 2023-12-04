package com.example.ticketservice.repository;

import com.example.ticketservice.model.RefundedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundedTicketRepository extends JpaRepository<RefundedTicket, Long> {
}
