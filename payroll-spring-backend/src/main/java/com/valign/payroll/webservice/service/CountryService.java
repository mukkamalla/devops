package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Country;

public interface CountryService {

	Country create(Country country);

	Country delete(int id);

	Page<Country> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Country> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Country findById(int id);

	Country update(Country country);

	public java.util.List<Country> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}