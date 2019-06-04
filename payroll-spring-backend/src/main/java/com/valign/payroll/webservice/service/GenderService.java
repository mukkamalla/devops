package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Gender;

public interface GenderService {

	Gender create(Gender gender);

	Gender delete(int id);

	Page<Gender> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Gender> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Gender findById(int id);

	Gender update(Gender gender);

	public java.util.List<Gender> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}