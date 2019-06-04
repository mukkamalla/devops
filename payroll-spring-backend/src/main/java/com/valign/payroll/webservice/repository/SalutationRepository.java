package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Salutation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SalutationRepository extends PagingAndSortingRepository<Salutation, Integer> {

	void delete(Salutation salutation);

	@Query("SELECT s FROM Salutation s WHERE  s.recordStatus = 1  AND  s.localeId = ?1  ")
	java.util.List<Salutation> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(s) FROM Salutation s WHERE  s.recordStatus = 1  AND  s.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	Salutation findById(int id);

	@Query("SELECT s FROM Salutation s WHERE ( s.recordStatus = 1 ) AND ( s.localeId = ?1 ) AND ( "
			+ " ( s.salutation LIKE '%'||?2||'%'  ) OR  " +  " ( s.id = ?2 ) ) ")
	Page<Salutation> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	Salutation save(Salutation salutation);
}