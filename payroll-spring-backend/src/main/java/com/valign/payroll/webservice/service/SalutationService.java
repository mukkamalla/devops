package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Salutation;

public interface SalutationService {

	Salutation create(Salutation salutation);

	Salutation delete(int id);

	Page<Salutation> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Salutation> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Salutation findById(int id);

	Salutation update(Salutation salutation);

	public java.util.List<Salutation> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}