package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

	void delete(Employee employee);


	@Query("SELECT COUNT(e) FROM Employee e WHERE  e.recordStatus = 1   ")
	Integer countRecords();

	Employee findById(int id);

	@Query("SELECT e FROM Employee e WHERE ( e.recordStatus = 1 )  AND ( "
			+ " ( e.aadharNumber LIKE '%'||?1||'%'   ) OR " 
			+ " ( e.bankAccountNumber LIKE '%'||?1||'%'  ) OR "  +
			" ( e.bankIFSCCode LIKE '%'||?1||'%'  ) OR "  +
			" ( e.employeeCode LIKE '%'||?1||'%'  ) OR "  +
			" ( e.firstName LIKE '%'||?1||'%'  ) OR "  +
			" ( e.lastName LIKE '%'||?1||'%'  ) OR "  +
			" ( e.mobileNumber LIKE '%'||?1||'%'  ) OR "  +
			" ( e.panNumber LIKE '%'||?1||'%'  ) OR "  +
			" ( e.id = ?1 ) ) ")
	Page<Employee> findBySearchTerm(String searchTerm, Pageable pagable);

	Employee save(Employee employee);
}