package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.RecordStatus;



public interface RecordStatusService {

	RecordStatus create(RecordStatus recordStatus);

	RecordStatus delete(int id);

	Page<RecordStatus> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<RecordStatus> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	RecordStatus findById(int id);

	RecordStatus update(RecordStatus recordStatus);

	public java.util.List<RecordStatus> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}