package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.CompanyLevelSalaryComponent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyLevelSalaryComponentRepository extends PagingAndSortingRepository<CompanyLevelSalaryComponent, Integer> {

	void delete(CompanyLevelSalaryComponent salaryComponent);


	@Query("SELECT COUNT(r) FROM CompanyLevelSalaryComponent r WHERE  r.recordStatus = 1   ")
	Integer countRecords();

	CompanyLevelSalaryComponent findById(int id);

	@Query("SELECT r FROM CompanyLevelSalaryComponent r WHERE ( r.recordStatus = 1 )  AND ( "
			+ " ( r.salaryComponentName LIKE %?1%  ) OR " + " ( r.salaryComponentName LIKE '%'||?1||'%'  ) OR " + " ( r.additionOrDeduction LIKE '%'||?1||'%'  ) OR "  +  " ( r.id = ?1 ) ) ")
	Page<CompanyLevelSalaryComponent> findBySearchTerm(String searchTerm, Pageable pagable);

	CompanyLevelSalaryComponent save(CompanyLevelSalaryComponent salaryComponent);
}