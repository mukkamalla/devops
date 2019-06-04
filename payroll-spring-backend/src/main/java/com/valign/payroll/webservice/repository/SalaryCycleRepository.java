package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.SalaryCycle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SalaryCycleRepository extends PagingAndSortingRepository<SalaryCycle, Integer> {

	void delete(SalaryCycle salaryCycle);


	@Query("SELECT COUNT(sc) FROM SalaryCycle sc WHERE  sc.recordStatus = 1   ")
	Integer countRecords();

	SalaryCycle findById(int id);

	@Query("SELECT sc FROM SalaryCycle sc WHERE ( sc.recordStatus = 1 )  AND ( "
			+ " ( sc.salaryCycleName LIKE %?1%  ) OR " + " ( sc.salaryCycleName LIKE '%'||?1||'%'  ) OR "  +  " ( sc.id = ?1 ) ) ")
	Page<SalaryCycle> findBySearchTerm(String searchTerm, Pageable pagable);

	SalaryCycle save(SalaryCycle salaryCycle);
}