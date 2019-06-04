package com.valign.payroll.webservice.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Language;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LanguageRepository extends PagingAndSortingRepository<Language, Integer> {

	void delete(Language language);


	@Query("SELECT COUNT(c) FROM Language c WHERE  c.recordStatus = 1   ")
	Integer countRecords();

	Language findById(int id);

	@Query("SELECT l FROM Language l WHERE ( l.recordStatus = 1 )  AND ( "
			+ " ( l.languageCode LIKE '%'||?1||'%'  ) OR " + " ( l.name LIKE '%'||?1||'%'  ) OR " + " ( l.nativeName LIKE '%'||?1||'%'  ) OR "  +  " ( l.id = ?1 ) ) ")
	Page<Language> findBySearchTerm(String searchTerm, Pageable pagable);

	Language save(Language language);
}