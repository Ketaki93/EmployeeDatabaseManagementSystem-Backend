package com.sample.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.management.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>,EmployeeRepositoryCustom{}
