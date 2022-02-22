package com.exercise.spring.model;

import javax.persistence.Embeddable;

@Embeddable
public class EmployeeDetails {
	    private String firstName;
	    private String lastName;
	    private String middleName;
	   
	public EmployeeDetails() {}

    public EmployeeDetails(String firstName, String lastName, String middleName) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
		}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



		@Override
	    public String toString() {
	    return "\t\t" +
	            "First Name:\n" + firstName + "\n\t\t" +
	            "Middle Name:\n" + middleName + "\n\t\t" +
	            "Last Name:\n" + lastName;
	    }
	}


