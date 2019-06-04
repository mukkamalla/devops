package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.RoleSalaryMaster;

public interface RoleSalaryMasterService {

	RoleSalaryMaster create(RoleSalaryMaster roleSalaryMaster);

	RoleSalaryMaster delete(int id);

	Page<RoleSalaryMaster> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<RoleSalaryMaster> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	RoleSalaryMaster findById(int id);

	RoleSalaryMaster update(RoleSalaryMaster roleSalaryMaster);

	public java.util.List<RoleSalaryMaster> findAll();

	public Integer countRecord();
}
