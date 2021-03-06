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
import com.exercise.spring.model.Employee;
import com.exercise.spring.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // add employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeService.addEmployee(employee);
    }

    // list employees
    @GetMapping("/all")
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }
    
    // get employees by id
    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) throws ResourceNotFoundException {
        Employee getEmployeeById = employeeService.getEmployeeById(employeeId, employee);
        return ResponseEntity.ok(getEmployeeById);
    }

     
    // list employees by last name asc
    @GetMapping("/lastname")
    public List<Employee> listEmployeeByLastnameAsc() {
        return this.employeeService.listEmployeeByLastNameAsc();
    }

    // edit employee
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) throws ResourceNotFoundException {
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee
    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee (ID: " + employeeId + ") deleted.", HttpStatus.OK);
    }
}


