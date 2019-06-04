package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Grade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GradeRepository extends PagingAndSortingRepository<Grade, Integer> {

	void delete(Grade grade);


	@Query("SELECT COUNT(g) FROM Grade g WHERE  g.recordStatus = 1   ")
	Integer countRecords();

	Grade findById(int id);

	@Query("SELECT g FROM Grade g WHERE ( g.recordStatus = 1 )  AND ( "
			+ " ( g.gradeName LIKE '%'||?1||'%'  ) OR "  + " ( g.gradeDesc LIKE '%'||?1||'%'  ) OR "  +  " ( g.id = ?1 ) ) ")
	Page<Grade> findBySearchTerm(String searchTerm, Pageable pagable);

	Grade save(Grade grade);
}