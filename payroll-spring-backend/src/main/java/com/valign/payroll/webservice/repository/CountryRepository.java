package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Country;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

	void delete(Country country);

	@Query("SELECT c FROM Country c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  ")
	java.util.List<Country> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(c) FROM Country c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	Country findById(int id);

	@Query("SELECT c FROM Country c WHERE ( c.recordStatus = 1 ) AND ( c.localeId = ?1 ) AND ( "
			+ " ( c.code LIKE %?2% ) OR " + " ( c.code LIKE '%'||?2||'%' ) OR " + " ( c.name LIKE '%'||?2||'%' ) OR " + " ( c.fullName LIKE '%'||?2||'%'  ) OR "  + " ( c.iso3 LIKE %?2%  ) OR " + " ( c.iso3 LIKE '%'||?2||'%'  ) OR " + " ( c.continentId = ?2 ) ) ")
	Page<Country> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Country save(Country country);
}