package com.exercise.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.spring.exception.ResourceNotFoundException;
import com.exercise.spring.model.Department;
import com.exercise.spring.model.Employee;
import com.exercise.spring.repository.DepartmentRepository;
import com.exercise.spring.repository.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Employee addEmployeeDepartment(Long employeeId, int departmentId) throws ResourceNotFoundException {
        Employee employeeAddDepartment = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee (ID: " + employeeId + " not found."));
        Department departmentToAdd = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department (ID: " + departmentId + " not found."));
        List<Department> employeeDepartment = employeeAddDepartment.getDepartment();
        List<Employee> departmentEmployees = departmentToAdd.getEmployee();
        employeeDepartment.add(departmentToAdd);
        departmentEmployees.add(employeeAddDepartment);
        return employeeRepository.save(employeeAddDepartment);
    }

    public List<Department> listDepartment() {
        return departmentRepository.findAll();
    }

    public Department updateDepartment(int departmentId, Department department) throws ResourceNotFoundException {
        Department updatedDepartment = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department (ID: " + departmentId + " not found."));

        updatedDepartment.setDepartment(department.getDepartment());
        return this.departmentRepository.save(updatedDepartment);
    }

    public void deleteDepartment(int departmentId) {
        if(departmentRepository.existsById(departmentId)) departmentRepository.deleteById(departmentId);
    }

    public Employee deleteEmployeeDepartment(Long employeeId, int departmentId) throws ResourceNotFoundException {
        Employee employeeDeleteDepartment = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee (ID: " + employeeId + " not found."));
        Department departmentToDelete = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department (ID: " + departmentId + " not found."));
        List<Department> employeeDepartment = employeeDeleteDepartment.getDepartment();

        employeeDepartment.remove(new Department(departmentToDelete.toString()));
        return employeeRepository.save(employeeDeleteDepartment);
    }
}

