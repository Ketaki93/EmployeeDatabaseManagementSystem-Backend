package com.sample.management.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.sample.management.model.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
	
	@PersistenceContext
	  private EntityManager em;

	public EmployeeRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public List<Employee> findEmployeeReporteesUsingId(Employee mgr) {
	      Query query = em.createQuery(
	              "SELECT e"
	              + "   FROM Employee AS e"
	              + "   WHERE e.manager = :empid",Employee.class).setParameter("empid", mgr);
	      List<Employee> resultList = query.getResultList();	      
	      em.close();
	      return resultList;
	}


}
