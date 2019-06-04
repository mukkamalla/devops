package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Continent;

public interface ContinentService {

	Continent create(Continent continent);

	Continent delete(int id);

	Page<Continent> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Continent> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Continent findById(int id);

	Continent update(Continent continent);

	public java.util.List<Continent> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}