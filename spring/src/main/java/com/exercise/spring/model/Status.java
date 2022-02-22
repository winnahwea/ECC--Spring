package com.exercise.spring.model;

public enum Status {

    NEW ("New"),
    ASSIGNED ("Assigned"),
    IN_PROGRESS ("In Progress"),
    CLOSED ("Closed");

    private final String name;

    Status(String name) {
    	this.name = name;}

    public String getName() {
    	return name;
    	}

}
	


