package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.Country;
import com.valign.payroll.webservice.model.Language;

public interface LanguageService {


	Language create(Language language);

	Language delete(int id);

	Page<Language> findAllSortOnColumn( String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Language> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Language findById(int id);

	Language update(Language language);

	public java.util.List<Language> findAll();
	public Integer countRecord();
}