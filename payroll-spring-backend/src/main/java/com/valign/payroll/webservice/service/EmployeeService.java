package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Employee;

public interface EmployeeService {

	Employee create(Employee employee);

	Employee delete(int id);

	Page<Employee> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Employee> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Employee findById(int id);

	Employee update(Employee employee);

	public java.util.List<Employee> findAll();

	public Integer countRecord();
}