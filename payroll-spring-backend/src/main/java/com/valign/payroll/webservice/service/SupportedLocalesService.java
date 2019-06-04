package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.SupportedLocales;

public interface SupportedLocalesService {

	SupportedLocales create(SupportedLocales supportedLocales);

	SupportedLocales delete(int id);

	Page<SupportedLocales> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<SupportedLocales> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	SupportedLocales findById(int id);

	SupportedLocales update(SupportedLocales supportedLocales);

	public java.util.List<SupportedLocales> findAll();

	public Integer countRecord();
}