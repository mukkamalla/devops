package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Shift;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShiftRepository extends PagingAndSortingRepository<Shift, Integer> {

	void delete(Shift shift);


	@Query("SELECT COUNT(s) FROM Shift s WHERE  s.recordStatus = 1   ")
	Integer countRecords();

	Shift findById(int id);

	@Query("SELECT s FROM Shift s WHERE ( s.recordStatus = 1 )  AND ( "
			+ " ( s.shiftName LIKE %?1%  ) OR " 	+ " ( s.shiftName LIKE '%'||?1||'%'  ) OR "   +  " ( s.id = ?1 ) ) ")
	Page<Shift> findBySearchTerm(String searchTerm, Pageable pagable);

	Shift save(Shift shift);
}