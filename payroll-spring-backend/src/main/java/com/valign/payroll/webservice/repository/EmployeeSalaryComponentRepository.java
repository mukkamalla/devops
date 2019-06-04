package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.EmployeeSalaryComponent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeSalaryComponentRepository extends PagingAndSortingRepository<EmployeeSalaryComponent, Integer> {

	void delete(EmployeeSalaryComponent employeeSalaryComponent);


	@Query("SELECT COUNT(esc) FROM EmployeeSalaryComponent esc WHERE  esc.recordStatus = 1   ")
	Integer countRecords();

	EmployeeSalaryComponent findById(int id);

	@Query("SELECT esc FROM EmployeeSalaryComponent esc WHERE ( esc.recordStatus = 1 )  AND  "
			+ " ( esc.employeeSalaryMasterId LIKE ?1   )  " )
	Page<EmployeeSalaryComponent> findBySearchTerm(String searchTerm, Pageable pagable);

	EmployeeSalaryComponent save(EmployeeSalaryComponent employeeSalaryComponent);
}