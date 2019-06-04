package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.DayValue;

public interface DayValueService {

	DayValue create(DayValue dayValue);

	DayValue delete(int id);

	Page<DayValue> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<DayValue> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	DayValue findById(int id);

	DayValue update(DayValue dayValue);

	public java.util.List<DayValue> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}