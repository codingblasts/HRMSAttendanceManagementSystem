package com.company.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.attendance.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}