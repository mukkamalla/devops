package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.EmployeeSalaryMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeSalaryMasterRepository extends PagingAndSortingRepository<EmployeeSalaryMaster, Integer> {

	void delete(EmployeeSalaryMaster employeeSalaryMaster);


	@Query("SELECT COUNT(esm) FROM EmployeeSalaryMaster esm WHERE  esm.recordStatus = 1   ")
	Integer countRecords();
	

	EmployeeSalaryMaster findById(int id);

	@Query("SELECT esm FROM EmployeeSalaryMaster esm WHERE ( esm.recordStatus = 1 )  AND ( "
			+ " ( esm.employeeId LIKE ?1  ) OR "  + " ( esm.employeeSalaryComponentName LIKE '%'||?1||'%'   ) OR "  +  " ( esm.id = ?1 ) ) ")
	Page<EmployeeSalaryMaster> findBySearchTerm(String searchTerm, Pageable pagable);

	EmployeeSalaryMaster save(EmployeeSalaryMaster employeeSalaryMaster);
}