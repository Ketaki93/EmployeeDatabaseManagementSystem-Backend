package com.sample.management.repository;

import java.util.List;

import com.sample.management.model.Employee;


public interface EmployeeRepositoryCustom {
	
	List<Employee> findEmployeeReporteesUsingId(Employee mgr);
}
