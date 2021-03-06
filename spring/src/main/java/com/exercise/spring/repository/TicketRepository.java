package com.exercise.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exercise.spring.model.Ticket;

	@Repository
	@Transactional
	public interface TicketRepository extends JpaRepository<Ticket, Long> {
		
	}


