package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.DayValue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DayValueRepository extends PagingAndSortingRepository<DayValue, Integer> {

	void delete(DayValue dayValue);

	@Query("SELECT dv FROM DayValue dv WHERE  dv.recordStatus = 1  AND  dv.localeId = ?1  ")
	java.util.List<DayValue> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(dv) FROM DayValue dv WHERE  dv.recordStatus = 1  AND  dv.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	DayValue findById(int id);

	@Query("SELECT dv FROM DayValue dv WHERE ( dv.recordStatus = 1 ) AND ( dv.localeId = ?1 ) AND ( "
			+ " ( dv.dayValueName LIKE '%'||?2||'%'   ) OR " + " ( dv.dayValueValue = ?2 ) OR " + " ( dv.id = ?2 ) ) ")
	Page<DayValue> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	DayValue save(DayValue dayValue);
}