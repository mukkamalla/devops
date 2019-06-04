package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Attendance;

public interface AttendanceService {

	Attendance create(Attendance attendance);

	Attendance delete(int id);

	Page<Attendance> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Attendance> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Attendance findById(int id);

	Attendance update(Attendance attendance);

	public java.util.List<Attendance> findAll();

	public Integer countRecord();
}