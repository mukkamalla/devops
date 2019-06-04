package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

	void delete(Company company);


	@Query("SELECT COUNT(c) FROM Company c WHERE  c.recordStatus = 1   ")
	Integer countRecords();

	Company findById(int id);

	@Query("SELECT c FROM Company c WHERE ( c.recordStatus = 1 )  AND ( "
			+ " ( c.companyName LIKE '%'||?1||'%'   ) OR "  +  " ( c.id = ?1 ) ) ")
	Page<Company> findBySearchTerm(String searchTerm, Pageable pagable);

	Company save(Company company);
}
