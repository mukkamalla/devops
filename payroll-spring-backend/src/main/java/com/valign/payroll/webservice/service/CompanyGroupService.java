package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.CompanyGroup;

public interface CompanyGroupService {


	CompanyGroup create(CompanyGroup companyGroup);

	CompanyGroup delete(int id);

	Page<CompanyGroup> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<CompanyGroup> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	CompanyGroup findById(int id);

	CompanyGroup update(CompanyGroup companyGroup);

	public java.util.List<CompanyGroup> findAll();
	public Integer countRecord();
}