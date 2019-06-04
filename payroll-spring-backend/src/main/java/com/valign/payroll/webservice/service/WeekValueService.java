package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.WeekValue;

public interface WeekValueService {

	WeekValue create(WeekValue weekValue);

	WeekValue delete(int id);

	Page<WeekValue> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<WeekValue> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	WeekValue findById(int id);

	WeekValue update(WeekValue weekValue);

	public java.util.List<WeekValue> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}