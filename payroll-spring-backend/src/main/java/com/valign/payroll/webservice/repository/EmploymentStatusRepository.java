package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.EmploymentStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmploymentStatusRepository extends PagingAndSortingRepository<EmploymentStatus, Integer> {

	void delete(EmploymentStatus employmentStatus);

	@Query("SELECT es FROM EmploymentStatus es WHERE  es.recordStatus = 1  AND  es.localeId = ?1  ")
	java.util.List<EmploymentStatus> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(es) FROM EmploymentStatus es WHERE  es.recordStatus = 1  AND  es.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	EmploymentStatus findById(int id);

	@Query("SELECT es FROM EmploymentStatus es WHERE ( es.recordStatus = 1 ) AND ( es.localeId = ?1 ) AND ( "
			+ " ( es.empStatus LIKE '%'||?2||'%'  ) OR " + " ( es.empStatusDesc LIKE '%'||?2||'%'  ) OR "  + " ( es.id = ?2 ) ) ")
	Page<EmploymentStatus> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	EmploymentStatus save(EmploymentStatus employmentStatus);
}