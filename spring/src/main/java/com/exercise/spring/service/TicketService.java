package com.exercise.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exercise.spring.exception.ResourceNotFoundException;
import com.exercise.spring.model.Ticket;
import com.exercise.spring.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //ADD
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    //GET
    public List<Ticket> getAllTicket(){
		return this.ticketRepository.findAll();
	
    }
    
    public Ticket getTicketById(Long id, Ticket ticket) throws ResourceNotFoundException {
    Ticket getTicketById = ticketRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Ticket (ID: " + id + " not found."));
	return getTicketById;
    }
    //UPDATE
    public Ticket updateTicket(Long id, Ticket ticket) throws ResourceNotFoundException {
        Ticket updatedTicket = ticketRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Ticket (ID: " + id + " not found."));

        updatedTicket.setTitle(ticket.getTitle());
        updatedTicket.setDescription(ticket.getDescription());
        updatedTicket.setAssignedTo(ticket.getAssignedTo());
        updatedTicket.setWatchers(ticket.getWatchers());
        updatedTicket.setTicketSeverity(ticket.getTicketSeverity());
        updatedTicket.setStatus(ticket.getStatus());
        
        return this.ticketRepository.save(updatedTicket);
    }

    //DELETE
    public void deleteTicket(Long id) throws ResourceNotFoundException {
        if(ticketRepository.existsById(id)) ticketRepository.deleteById(id);
    }
    
}
