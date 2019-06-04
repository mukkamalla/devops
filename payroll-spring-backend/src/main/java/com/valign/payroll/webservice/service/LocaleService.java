package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Locale;;

public interface LocaleService {


	Locale create(Locale locale);

	Locale delete(int id);

	Page<Locale> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Locale> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Locale findById(int id);

	Locale update(Locale locale);

	public java.util.List<Locale> findAll();
	public Integer countRecord();
}