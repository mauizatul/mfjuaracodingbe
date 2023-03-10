package com.juaracoding.mf.repo;


import com.juaracoding.mf.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}