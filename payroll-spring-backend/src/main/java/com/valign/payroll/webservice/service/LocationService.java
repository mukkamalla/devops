package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Location;

public interface LocationService {

	Location create(Location location);

	Location delete(int id);

	Page<Location> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Location> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	Location findById(int id);

	Location update(Location location);

	public java.util.List<Location> findAll();

	public Integer countRecord();
}
