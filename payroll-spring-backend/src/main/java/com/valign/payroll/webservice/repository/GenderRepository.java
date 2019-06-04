package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Gender;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenderRepository extends PagingAndSortingRepository<Gender, Integer> {

	void delete(Gender gender);

	@Query("SELECT g FROM Gender g WHERE  g.recordStatus = 1  AND  g.localeId = ?1  ")
	java.util.List<Gender> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(g) FROM Gender g WHERE  g.recordStatus = 1  AND  g.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	Gender findById(int id);

	@Query("SELECT g FROM Gender g WHERE ( g.recordStatus = 1 ) AND ( g.localeId = ?1 ) AND ( "
			+ " ( g.gender LIKE '%'||?2||'%'  ) OR "  + " ( g.id = ?2 ) ) ")
	Page<Gender> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Gender save(Gender gender);
}