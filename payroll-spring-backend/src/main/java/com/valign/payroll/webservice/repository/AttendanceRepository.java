package com.valign.payroll.webservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.valign.payroll.webservice.model.Attendance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttendanceRepository extends PagingAndSortingRepository<Attendance, Integer> {

	void delete(Attendance attendance);


	@Query("SELECT COUNT(a) FROM Attendance a WHERE  a.recordStatus = 1   ")
	Integer countRecords();

	Attendance findById(int id);

	@Query("SELECT a FROM Attendance a WHERE ( a.recordStatus = 1 )  AND ( "
			+ " ( a.attendanceDate = ?1 ) OR "   +  " ( a.employeeId = ?1 ) ) ")
	Page<Attendance> findBySearchTerm(String searchTerm, Pageable pagable);

	Attendance save(Attendance attendance);
}