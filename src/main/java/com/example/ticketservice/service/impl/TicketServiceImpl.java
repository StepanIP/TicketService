package com.example.ticketservice.service.impl;

import com.example.ticketservice.exception.NullEntityReferenceException;
import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.model.User;
import com.example.ticketservice.repository.TicketRepository;
import com.example.ticketservice.service.TicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(Ticket ticket) {
        if (ticket != null) {
            return ticketRepository.save(ticket);
        }
        throw new NullEntityReferenceException("Ticket cannot be 'null'");
    }

    @Override
    public Ticket readById(long id) {
        return ticketRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ticket with id " + id + " not found"));
    }

    @Override
    public Ticket update(Ticket ticket) {
        if (ticket != null) {
            readById(ticket.getId());
            return ticketRepository.save(ticket);
        }
        throw new NullEntityReferenceException("Ticket cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        ticketRepository.delete(readById(id));
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.isEmpty() ? new ArrayList<>() : tickets;
    }

    @Override
    public List<Ticket> findTicketByUser(User user) {
        return ticketRepository.getTicketByUser(user);
    }

}
