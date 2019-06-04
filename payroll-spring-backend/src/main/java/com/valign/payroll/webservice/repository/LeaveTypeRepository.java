package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.LeaveType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveTypeRepository extends PagingAndSortingRepository<LeaveType, Integer> {

	void delete(LeaveType leaveType);


	@Query("SELECT COUNT(lt) FROM LeaveType lt WHERE  lt.recordStatus = 1   ")
	Integer countRecords();

	LeaveType findById(int id);

	@Query("SELECT lt FROM LeaveType lt WHERE ( lt.recordStatus = 1 )  AND ( "
			+ " ( lt.leaveTypeName LIKE '%'||?1||'%'  ) OR "    +  " ( lt.id = ?1 ) ) ")
	Page<LeaveType> findBySearchTerm(String searchTerm, Pageable pagable);

	LeaveType save(LeaveType leaveType);
}