package com.sample.management.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.management.exception.model.EmployeeRecordNotFoundException;
import com.sample.management.model.Employee;
import com.sample.management.model.Manager;
import com.sample.management.repository.EmployeeRepository;
import com.sample.management.repository.EmployeeRepositoryImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class EmployeeRestController {

@Autowired
EmployeeRepository employeeRepository;

@Autowired
EmployeeRepositoryImpl employeeRepositoryImpl;
	
@GetMapping("/employees")
public ResponseEntity<List<Employee>> getAllEmployees() {
	try {
	      List<Employee> employeeList = new ArrayList<Employee>();
	      employeeRepository.findAll().forEach(employeeList::add);	     
	      if (employeeList.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(employeeList, HttpStatus.OK);
	      
	    } catch (Exception e) {
	    	throw new EmployeeRecordNotFoundException("Employee table has zero records");
	    }
}

@GetMapping("/employees/{empid}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable("empid") long empid) {
  Optional<Employee> employeeData = employeeRepository.findById(empid);

  if (employeeData.isPresent()) {
    return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
  } else {
	  throw new EmployeeRecordNotFoundException("Employee record with id = "+empid+" not found");
  }
  
}

@PostMapping("/employees")
public ResponseEntity<Employee> addEmployee( @RequestBody Manager empCustom) {
  try {	  	 
	  Employee emp = new Employee(empCustom.getFirstname(),empCustom.getLastname());
	  Optional<Employee> mgr = employeeRepository.findById(empCustom.getManagerId());
	  if(mgr.isPresent())
		  emp.setManager(mgr.get());
	  
	  employeeRepository.save(emp);	 
    return new ResponseEntity<>(emp, HttpStatus.CREATED);
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

@PutMapping("/employees/{empid}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("empid") long empid, @RequestBody Manager employee) throws ParseException {
  Optional<Employee> employeeData = employeeRepository.findById(empid);
  if (employeeData.isPresent()) {
	Employee emp = employeeData.get();
	if(!employee.getFirstname().isBlank())
    emp.setFirstname(employee.getFirstname());
	if(!employee.getLastname().isBlank())
    emp.setLastname(employee.getLastname());
	
	Optional<Employee> mgrData = employeeRepository.findById(employee.getManagerId());
	if(mgrData.isPresent()) {
		Employee mgr = mgrData.get();				
		if(empid != mgr.getEmployeeId() && null == mgr.getManager()){
			emp.setManager(mgr);
		}else if(empid != mgr.getEmployeeId() && empid != mgr.getManager().getEmployeeId()) {
			emp.setManager(mgr);
		}
			
	}
    return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
  } else {
    throw new EmployeeRecordNotFoundException("Employee record with id = "+empid+" not found");
  }
}

@GetMapping("/employees/{empid}/managers")
public ResponseEntity<Employee> getEmployeeManagerById(@PathVariable("empid") long empid) {
  Optional<Employee> employeeData = employeeRepository.findById(empid);
  if (employeeData.isPresent()) {
    return new ResponseEntity<>(employeeData.get().getManager(), HttpStatus.OK);
  } else {
	  throw new EmployeeRecordNotFoundException("Employee record with id = "+empid+" not found");
  }
  
}

@GetMapping("/employees/{empid}/reportees")
public ResponseEntity<List<Employee>> getEmployeeReporteeById(@PathVariable("empid") long empid) {
	Employee mgr = null;
	Optional<Employee> employeeData = employeeRepository.findById(empid);
	  if (employeeData.isPresent()) {
	   mgr = employeeData.get();
	  }
   List<Employee> reporteeData = employeeRepositoryImpl.findEmployeeReporteesUsingId(mgr);

  if (null != employeeData) {
    return new ResponseEntity<>(reporteeData, HttpStatus.OK);
  } else {
	  throw new EmployeeRecordNotFoundException("Employee record with id = "+empid+" not found");
  }
  
}

}
