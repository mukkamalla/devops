package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.SalaryCycle;

public interface SalaryCycleService {

	SalaryCycle create(SalaryCycle salaryCycle);

	SalaryCycle delete(int id);

	Page<SalaryCycle> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<SalaryCycle> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	SalaryCycle findById(int id);

	SalaryCycle update(SalaryCycle salaryCycle);

	public java.util.List<SalaryCycle> findAll();

	public Integer countRecord();
}