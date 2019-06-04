package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.EmploymentStatus;;

public interface EmploymentStatusService {

	EmploymentStatus create(EmploymentStatus employmentStatus);

	EmploymentStatus delete(int id);

	Page<EmploymentStatus> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<EmploymentStatus> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	EmploymentStatus findById(int id);

	EmploymentStatus update(EmploymentStatus employmentStatus);

	public java.util.List<EmploymentStatus> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}