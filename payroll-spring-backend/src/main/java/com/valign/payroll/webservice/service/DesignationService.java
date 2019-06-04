package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Designation;

public interface DesignationService {

	Designation create(Designation designation);

	Designation delete(int id);

	Page<Designation> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Designation> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Designation findById(int id);

	Designation update(Designation designation);

	public java.util.List<Designation> findAll();

	public Integer countRecord();
}