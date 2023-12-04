package com.example.ticketservice.service.impl;

import com.example.ticketservice.exception.NullEntityReferenceException;
import com.example.ticketservice.model.RefundedTicket;
import com.example.ticketservice.repository.RefundedTicketRepository;
import com.example.ticketservice.service.RefundedTicketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RefundedTicketServiceImpl implements RefundedTicketService {

    private final RefundedTicketRepository refundedTicketRepository;

    public RefundedTicketServiceImpl(RefundedTicketRepository refundedTicketRepository) {
        this.refundedTicketRepository = refundedTicketRepository;
    }

    @Override
    public RefundedTicket create(RefundedTicket refundedTicket) {
        if (refundedTicket != null) {
            return refundedTicketRepository.save(refundedTicket);
        }
        throw new NullEntityReferenceException("RefundedTicket cannot be 'null'");
    }

    @Override
    public RefundedTicket readById(long id) {
        return refundedTicketRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("RefundedTicket with id " + id + " not found"));
    }

    @Override
    public RefundedTicket update(RefundedTicket refundedTicket) {
        if (refundedTicket != null) {
            readById(refundedTicket.getId());
            return refundedTicketRepository.save(refundedTicket);
        }
        throw new NullEntityReferenceException("RefundedTicket cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        refundedTicketRepository.delete(readById(id));
    }

    @Override
    public List<RefundedTicket> getAll() {
        List<RefundedTicket> refundedTickets = refundedTicketRepository.findAll();
        return refundedTickets.isEmpty() ? new ArrayList<>() : refundedTickets;
    }

}
