package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Timezone;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TimezoneRepository extends PagingAndSortingRepository<Timezone, Integer> {

	void delete(Timezone timezone);

	@Query("SELECT t FROM Timezone t WHERE  t.recordStatus = 1  AND  t.localeId = ?1  ")
	java.util.List<Timezone> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(t) FROM Timezone t WHERE  t.recordStatus = 1  AND  t.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	Timezone findById(int id);

	@Query("SELECT t FROM Timezone t WHERE ( t.recordStatus = 1 ) AND ( t.localeId = ?1 ) AND ( "
			+ " ( t.gmtOffset LIKE '%'||?2||'%'  ) OR " + " ( t.timeZoneId LIKE '%'||?2||'%'  ) OR "  + " ( t.id = ?2 ) ) ")
	Page<Timezone> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Timezone save(Timezone timezone);
}