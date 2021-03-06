package com.exercise.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="department_id",unique=true, updatable = false, nullable = false)
    private int departmentId;

    private String department;
    
    @ManyToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Employee> employee = new ArrayList<>();
    
    public Department(){
    }

    public Department(String department) {
		super();
		this.department = department;
	}

	public int getId() {
        return departmentId;
    }

    public void setId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public List<Employee> getEmployee() {
        return employee;
    }
    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
    @Override
    public String toString() {
        return department;
    }


}