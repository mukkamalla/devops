package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.WeekValue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WeekValueRepository extends PagingAndSortingRepository<WeekValue, Integer> {

	void delete(WeekValue weekValue);

	@Query("SELECT wv FROM WeekValue wv WHERE  wv.recordStatus = 1  AND  wv.localeId = ?1  ")
	java.util.List<WeekValue> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(wv) FROM WeekValue wv WHERE  wv.recordStatus = 1  AND  wv.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	WeekValue findById(int id);

	@Query("SELECT wv FROM WeekValue wv WHERE ( wv.recordStatus = 1 ) AND ( wv.localeId = ?1 ) AND ( "
			+ " ( wv.weekValueName LIKE '%'||?2||'%'  ) OR " + " ( wv.id = ?2 ) ) ")
	Page<WeekValue> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	WeekValue save(WeekValue weekValue);
}