package com.exercise.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee  {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id",unique=true, updatable = false, nullable = false)
    private Long employeeId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
    })
    private EmployeeDetails employeeDetails;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="employee_id")
    private List<Department> department = new ArrayList<>();

    public Employee() {}

    public Employee( EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
   
    public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public List<Department> getDepartment() {
        return department;
    }
    public void setDepartment(List<Department> department) {
        this.department = department;
    }
    
    @Override
    public String toString() {
        return  "[Id: " + employeeId + "] " + employeeDetails + "\n\t" +
                "Department:\n" + department ;
    }

	
}

