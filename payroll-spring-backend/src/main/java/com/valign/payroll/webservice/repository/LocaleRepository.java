package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Locale;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocaleRepository extends PagingAndSortingRepository<Locale, Integer> {

	void delete(Locale locale);


	@Query("SELECT COUNT(l) FROM Locale l WHERE  l.recordStatus = 1   ")
	Integer countRecords();

	Locale findById(int id);

	@Query("SELECT l FROM Locale l WHERE ( l.recordStatus = 1 )  AND ( "
			+ " ( l.localeName LIKE '%'||?1||'%'  ) OR "  +  " ( l.id = ?1 ) ) ")
	Page<Locale> findBySearchTerm(String searchTerm, Pageable pagable);

	Locale save(Locale locale);
}