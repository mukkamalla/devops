package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import com.valign.payroll.webservice.model.Continent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ContinentRepository extends PagingAndSortingRepository<Continent, Integer> {

	void delete(Continent continent);
	
	@Query("SELECT c FROM Continent c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  "  )
	java.util.List<Continent> findAllByLocaleId(int localeId);
	
	@Query("SELECT COUNT(c) FROM Continent c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  "  )
	Integer countRecordsByLocaleId(int localeId);

	Continent findById(int id);

	@Query("SELECT c FROM Continent c WHERE ( c.recordStatus = 1 ) AND ( c.localeId = ?1 ) AND ( " 
	+ " ( c.continentCode LIKE '%'||?2||'%'   ) OR " + " ( c.continentName LIKE '%'||?2||'%'  ) ) "
				 )
	Page<Continent> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Continent save(Continent continent);
}