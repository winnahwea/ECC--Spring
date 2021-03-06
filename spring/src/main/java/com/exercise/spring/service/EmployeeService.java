package com.exercise.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exercise.spring.exception.ResourceNotFoundException;
import com.exercise.spring.model.Employee;
import com.exercise.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //ADD
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //LIST
    public List<Employee> getAllEmployee(){
		return this.employeeRepository.findAll();
    }
   
    public List<Employee> listEmployeeByLastNameAsc() {
        Optional<List<Employee>> employeeByLastName = employeeRepository.listEmployeeByLastNameAsc();
        return employeeByLastName.orElse(null);
    }
    public Employee getEmployeeById(Long employeeId, Employee employee) throws ResourceNotFoundException {
        Employee getEmployeeById = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee (ID: " + employeeId + " not found."));
		return getEmployeeById;
    }
    //UPDATE
    public Employee updateEmployee(Long employeeId, Employee employee) throws ResourceNotFoundException {
        Employee updatedEmployee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee (ID: " + employeeId + " not found."));

        updatedEmployee.getEmployeeDetails().setFirstName(employee.getEmployeeDetails().getFirstName());
        updatedEmployee.getEmployeeDetails().setMiddleName(employee.getEmployeeDetails().getMiddleName());
        updatedEmployee.getEmployeeDetails().setLastName(employee.getEmployeeDetails().getLastName());

        return this.employeeRepository.save(updatedEmployee);
    }

    //DELETE
    public void deleteEmployee(Long employeeId) throws ResourceNotFoundException {
        if(employeeRepository.existsById(employeeId)) employeeRepository.deleteById(employeeId);
    }


}
