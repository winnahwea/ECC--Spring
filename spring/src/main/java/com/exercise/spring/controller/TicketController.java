package com.exercise.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.spring.exception.ResourceNotFoundException;
import com.exercise.spring.model.Ticket;
import com.exercise.spring.service.TicketService;

@RestController
@RequestMapping("api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // add ticket
    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return this.ticketService.addTicket(ticket);
    }

    // list ticket
    @GetMapping("/all")
    public List<Ticket> getAllTicket() {
        return this.ticketService.getAllTicket();
    }
  
    // get ticket by id
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id, @RequestBody Ticket ticket) throws ResourceNotFoundException {
        Ticket getTicketById = ticketService.getTicketById(id, ticket);
        return ResponseEntity.ok(getTicketById);
    
    }
    // edit ticket
    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) throws ResourceNotFoundException {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    // delete ticket
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id) throws ResourceNotFoundException {
    	ticketService.deleteTicket(id);
        return new ResponseEntity<>("Ticket (ID: " + id + ") deleted.", HttpStatus.OK);
    }
    
}

