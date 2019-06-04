package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Department;

public interface DepartmentService {

	Department create(Department department);

	Department delete(int id);

	Page<Department> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Department> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Department findById(int id);

	Department update(Department department);

	public java.util.List<Department> findAll();

	public Integer countRecord();
}
