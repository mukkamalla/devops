package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.BusinessUnit;

public interface BusinessUnitService {

	BusinessUnit create(BusinessUnit businessUnits);

	BusinessUnit delete(int id);

	Page<BusinessUnit> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<BusinessUnit> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	BusinessUnit findById(int id);

	BusinessUnit update(BusinessUnit businessUnits);

	public java.util.List<BusinessUnit> findAll();

	public Integer countRecord();
}