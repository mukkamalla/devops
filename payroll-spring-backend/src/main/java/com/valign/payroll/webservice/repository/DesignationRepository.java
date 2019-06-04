package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Designation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DesignationRepository extends PagingAndSortingRepository<Designation, Integer> {

	void delete(Designation designation);


	@Query("SELECT COUNT(d) FROM Designation d WHERE  d.recordStatus = 1   ")
	Integer countRecords();

	Designation findById(int id);

	@Query("SELECT d FROM Designation d WHERE ( d.recordStatus = 1 )  AND ( "
			+ " ( d.designationCode LIKE '%'||?1||'%'   ) OR "  + " ( d.designationName LIKE '%'||?1||'%'   ) OR "  +  " ( d.id = ?1 ) ) ")
	Page<Designation> findBySearchTerm(String searchTerm, Pageable pagable);

	Designation save(Designation designation);
}