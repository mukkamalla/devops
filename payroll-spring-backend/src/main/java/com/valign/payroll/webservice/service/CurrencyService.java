package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Currency;

public interface CurrencyService {

	Currency create(Currency currency);

	Currency delete(int id);

	Page<Currency> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Currency> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Currency findById(int id);

	Currency update(Currency currency);

	public java.util.List<Currency> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}