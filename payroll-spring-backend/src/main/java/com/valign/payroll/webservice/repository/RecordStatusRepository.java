package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.RecordStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecordStatusRepository extends PagingAndSortingRepository<RecordStatus, Integer> {

	void delete(RecordStatus recordStatus);

	@Query("SELECT rs FROM RecordStatus rs WHERE   rs.localeId = ?1  ")
	java.util.List<RecordStatus> findAllByLocaleId(int localeId);

	@Query("SELECT COUNT(rs) FROM RecordStatus rs WHERE  rs.localeId = ?1  ")
	Integer countRecordsByLocaleId(int localeId);

	RecordStatus findById(int id);

	@Query("SELECT rs FROM RecordStatus rs WHERE  ( rs.localeId = ?1 ) AND ( "
			+ " ( rs.status LIKE '%'||?2||'%'  ) OR " + " ( rs.id = ?2 ) ) ")
	Page<RecordStatus> findBySearchTermByLocaleId(int localeId, String searchTerm, Pageable pagable);

	RecordStatus save(RecordStatus recordStatus);
}