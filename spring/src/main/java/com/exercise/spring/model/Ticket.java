package com.exercise.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket" )
public class Ticket {
	
	@Id
	@Column(name="ticket_id",unique=true, updatable = false, nullable = false)  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
						
	@Column(name= "title")
	private String title;
	
	@Column(name= "description")
	private String description;
	
	@ManyToOne
	private Employee assignedTo;
	
	@ManyToMany
	private List<Employee> watchers = new ArrayList<>();

	private TicketSeverity severity;
	
	private Status status;
	
	public Ticket() {
	}
	
	public Ticket(String title, String description) {
		this.title = title;
		this.description = description;
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;

	}
	public Employee getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo= assignedTo;
	}

	public List<Employee> getWatchers() {
		return watchers;
	}

	public void setWatchers(List<Employee> watchers) {
		this.watchers = watchers;
	}

	public TicketSeverity getTicketSeverity() {
		return severity;
	}

	public void setTicketSeverity(TicketSeverity severity) {
		this.severity = severity;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	 @Override
	public String toString() {
		  return  "Ticket [Number: " + id + "] " + "\n\t" +
		          "Title:\n" + title + "\n\t" +
		          "Description:\n" + description + "\n\t" +
		          "Assigned To:\n" + assignedTo + "\n\t" +
		          "Watchers To:\n" + watchers + "\n\t" +
		          "Severity:\n" + severity + "\n\t" +
		           "Status:\n" + status ;	
    }
}

