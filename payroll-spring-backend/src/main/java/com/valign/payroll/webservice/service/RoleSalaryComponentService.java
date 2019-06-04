package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.RoleSalaryComponent;

public interface RoleSalaryComponentService {

	RoleSalaryComponent create(RoleSalaryComponent roleSalaryComponent);

	RoleSalaryComponent delete(int id);

	Page<RoleSalaryComponent> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<RoleSalaryComponent> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	RoleSalaryComponent findById(int id);

	RoleSalaryComponent update(RoleSalaryComponent roleSalaryComponent);

	public java.util.List<RoleSalaryComponent> findAll();

	public Integer countRecord();
	
}