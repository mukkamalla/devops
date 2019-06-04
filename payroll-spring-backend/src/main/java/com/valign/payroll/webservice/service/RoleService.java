package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Role;

public interface RoleService {

	Role create(Role role);

	Role delete(int id);

	Page<Role> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Role> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Role findById(int id);

	Role update(Role role);

	public java.util.List<Role> findAll();

	public Integer countRecord();
}
