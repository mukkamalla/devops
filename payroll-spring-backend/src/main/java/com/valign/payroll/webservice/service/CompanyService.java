package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Company;

public interface CompanyService {

	Company create(Company company);

	Company delete(int id);

	Page<Company> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Company> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Company findById(int id);

	Company update(Company company);

	public java.util.List<Company> findAll();

	public Integer countRecord();
}