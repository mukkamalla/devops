package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.CompanyLevelSalaryComponent;

public interface CompanyLevelSalaryComponentService {

	CompanyLevelSalaryComponent create(CompanyLevelSalaryComponent salaryComponent);

	CompanyLevelSalaryComponent delete(int id);

	Page<CompanyLevelSalaryComponent> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<CompanyLevelSalaryComponent> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	CompanyLevelSalaryComponent findById(int id);

	CompanyLevelSalaryComponent update(CompanyLevelSalaryComponent salaryComponent);

	public java.util.List<CompanyLevelSalaryComponent> findAll();

	public Integer countRecord();
}