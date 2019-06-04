package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.HolidayMaster;

public interface HolidayMasterService {

	HolidayMaster create(HolidayMaster holidayMaster);

	HolidayMaster delete(int id);

	Page<HolidayMaster> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<HolidayMaster> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	HolidayMaster findById(int id);

	HolidayMaster update(HolidayMaster holidayMaster);

	public java.util.List<HolidayMaster> findAll();

	public Integer countRecord();
}
