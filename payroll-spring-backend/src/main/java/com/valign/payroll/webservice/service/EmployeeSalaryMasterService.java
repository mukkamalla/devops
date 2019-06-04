package com.valign.payroll.webservice.service;
import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.EmployeeSalaryMaster;;

public interface EmployeeSalaryMasterService {

	EmployeeSalaryMaster create(EmployeeSalaryMaster employeeSalaryMaster);

	EmployeeSalaryMaster delete(int id);

	Page<EmployeeSalaryMaster> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<EmployeeSalaryMaster> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	EmployeeSalaryMaster findById(int id);

	EmployeeSalaryMaster update(EmployeeSalaryMaster employeeSalaryMaster);

	public java.util.List<EmployeeSalaryMaster> findAll();

	public Integer countRecord();
}