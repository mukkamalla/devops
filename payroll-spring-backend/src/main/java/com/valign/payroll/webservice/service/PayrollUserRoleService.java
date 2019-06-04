package com.valign.payroll.webservice.service;



import java.util.List;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.PayrollUserRole;
import com.valign.payroll.webservice.model.Role;
import com.valign.payroll.webservice.util.SearchCriteria;

public interface PayrollUserRoleService {

	PayrollUserRole create(PayrollUserRole payrollUserRole);

	PayrollUserRole delete(int id);

	Page<PayrollUserRole> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<PayrollUserRole> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	PayrollUserRole findById(int id);

	PayrollUserRole update(PayrollUserRole payrollUserRole);

	public java.util.List<PayrollUserRole> findAll();
	
	public Integer countRecord();
}
