package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.SupportedLocales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupportedLocalesRepository extends PagingAndSortingRepository<SupportedLocales, Integer> {

	void delete(SupportedLocales supportedLocales);


	@Query("SELECT COUNT(sl) FROM SupportedLocales sl WHERE  sl.recordStatus = 1   ")
	Integer countRecords();

	SupportedLocales findById(int id);

	@Query("SELECT sl FROM SupportedLocales sl WHERE ( sl.recordStatus = 1 )  AND ( "
			   +  " ( sl.id = ?1 ) ) ")
	Page<SupportedLocales> findBySearchTerm(String searchTerm, Pageable pagable);

	SupportedLocales save(SupportedLocales supportedLocales);
}