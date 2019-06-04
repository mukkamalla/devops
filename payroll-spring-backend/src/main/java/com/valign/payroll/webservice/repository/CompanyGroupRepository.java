package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.CompanyGroup;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyGroupRepository extends PagingAndSortingRepository<CompanyGroup, Integer> {

	void delete(CompanyGroup companyGroup);


	@Query("SELECT COUNT(cg) FROM CompanyGroup cg WHERE  cg.recordStatus = 1   ")
	Integer countRecords();

	CompanyGroup findById(int id);

	@Query("SELECT cg FROM CompanyGroup cg WHERE ( cg.recordStatus = 1 )  AND ( "
			+ " ( cg.companyGroupName LIKE '%'||?1||'%'   ) OR "+ " ( cg.companyGroupDesc LIKE '%'||?1||'%'   ) OR "  +  " ( cg.id = ?1 ) ) ")
	Page<CompanyGroup> findBySearchTerm(String searchTerm, Pageable pagable);

	CompanyGroup save(CompanyGroup companyGroup);
}