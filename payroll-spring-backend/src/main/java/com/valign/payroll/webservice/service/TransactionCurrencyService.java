package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.TransactionCurrency;

public interface TransactionCurrencyService {

	TransactionCurrency create(TransactionCurrency transactionCurrency);

	TransactionCurrency delete(int id);

	Page<TransactionCurrency> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<TransactionCurrency> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	TransactionCurrency findById(int id);

	TransactionCurrency update(TransactionCurrency transactionCurrency);

	public java.util.List<TransactionCurrency> findAll();

	public Integer countRecord();
}