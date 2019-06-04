package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.HolidayMaster;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HolidayMasterRepository extends PagingAndSortingRepository<HolidayMaster, Integer> {

	void delete(HolidayMaster holidayMaster);


	@Query("SELECT COUNT(hm) FROM HolidayMaster hm WHERE  hm.recordStatus = 1 ")
	Integer countRecords();

	HolidayMaster findById(int id);

	@Query("SELECT hm FROM HolidayMaster hm WHERE ( hm.recordStatus = 1 )  AND ( "
			+ " ( hm.holidayName LIKE %?1%  ) OR "+ " ( hm.holidayName LIKE '%'||?1||'%'  ) OR "  + " ( hm.holidayDesc LIKE '%'||?1||'%'  ) OR "  +  " ( hm.id = ?1 ) ) ")
	Page<HolidayMaster> findBySearchTerm(String searchTerm, Pageable pagable);

	HolidayMaster save(HolidayMaster holidayMaster);
}