package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Grade;

public interface GradeService {

	Grade create(Grade grade);

	Grade delete(int id);

	Page<Grade> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Grade> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Grade findById(int id);

	Grade update(Grade grade);

	public java.util.List<Grade> findAll();

	public Integer countRecord();
}