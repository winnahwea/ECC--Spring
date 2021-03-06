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
import com.exercise.spring.model.Department;
import com.exercise.spring.model.Employee;
import com.exercise.spring.service.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //ADD DEPARTMENT
    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return this.departmentService.addDepartment(department);
    }

    //ADD DEPARTMENT TO EMPLOYEE
    @PostMapping("/addTo/{employeeId}/{departmentId}")
    public ResponseEntity<Employee> addEmployeeDepartment(@PathVariable("employeeId") Long employeeId,
                                                    @PathVariable("departmentId") int departmentId) throws ResourceNotFoundException {
        Employee addedEmployeeDepartment = departmentService.addEmployeeDepartment(employeeId,departmentId);
        return ResponseEntity.ok(addedEmployeeDepartment);
    }

    //EDIT DEPARTMENT
    @PutMapping("/update/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("departmentId") int departmentId,
                                            @RequestBody Department department) throws ResourceNotFoundException {
        Department updatedDepartment = departmentService.updateDepartment(departmentId, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    // LIST DEPARTMENT
    @GetMapping("/all")
    public List<Department> listDepartment() {
        return this.departmentService.listDepartment();
    }

    //DELETE DEPARTMENT
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") int departmentId) {
    	departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>("Department (ID: " + departmentId + ") deleted.", HttpStatus.OK);
    }

    //DELETE DEPARTMENT FROM EMPLOYEE
    @PostMapping("/deleteFrom/{employeeId}/{departmentId}")
    public ResponseEntity<Employee> deleteEmployeeDepartment(@PathVariable("employeeId") Long employeeId,
                                                        @PathVariable("departmentId") int departmentId) throws ResourceNotFoundException {
        Employee deleteFrom = departmentService.deleteEmployeeDepartment(employeeId,departmentId);
        return ResponseEntity.ok(deleteFrom);
    }


}
