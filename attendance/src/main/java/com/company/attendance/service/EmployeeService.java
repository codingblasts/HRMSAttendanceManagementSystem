package com.company.attendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.attendance.entity.Employee;
import com.company.attendance.repository.EmployeeRepository;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


  
 }
