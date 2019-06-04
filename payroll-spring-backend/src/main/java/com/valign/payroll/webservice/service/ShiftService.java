package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Shift;

public interface ShiftService {

	Shift create(Shift shift);

	Shift delete(int id);

	Page<Shift> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Shift> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Shift findById(int id);

	Shift update(Shift shift);

	public java.util.List<Shift> findAll();

	public Integer countRecord();
}