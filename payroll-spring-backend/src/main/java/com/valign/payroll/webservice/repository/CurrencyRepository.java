package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Currency;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Integer> {

	void delete(Currency currency);

	@Query("SELECT c FROM Currency c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  ")
	java.util.List<Currency> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(c) FROM Currency c WHERE  c.recordStatus = 1  AND  c.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	Currency findById(int id);

	@Query("SELECT c FROM Currency c WHERE ( c.recordStatus = 1 ) AND ( c.localeId = ?1 ) AND ( "
			+ " ( c.code LIKE '%'||?2||'%'   ) OR " + " ( c.currency LIKE '%'||?2||'%'   ) OR " +  " ( c.country LIKE '%'||?2||'%'   ) OR " + " ( c.symbol LIKE '%'||?2||'%'   ) OR  "  + " ( c.id = ?2 ) ) ")
	Page<Currency> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Currency save(Currency currency);
}