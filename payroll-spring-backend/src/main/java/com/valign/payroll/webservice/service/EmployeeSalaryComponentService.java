package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.EmployeeSalaryComponent;

public interface EmployeeSalaryComponentService {

	EmployeeSalaryComponent create(EmployeeSalaryComponent employeeSalaryComponent);

	EmployeeSalaryComponent delete(int id);

	Page<EmployeeSalaryComponent> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<EmployeeSalaryComponent> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	EmployeeSalaryComponent findById(int id);

	EmployeeSalaryComponent update(EmployeeSalaryComponent employeeSalaryComponent);

	public java.util.List<EmployeeSalaryComponent> findAll();

	public Integer countRecord();
}