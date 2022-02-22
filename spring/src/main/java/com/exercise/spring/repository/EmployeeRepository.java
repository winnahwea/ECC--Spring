package com.exercise.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exercise.spring.model.Employee;

@Repository 
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query(value = "SELECT * FROM employee ORDER BY last_name ASC", nativeQuery = true)
    Optional<List<Employee>> listEmployeeByLastNameAsc();


}
