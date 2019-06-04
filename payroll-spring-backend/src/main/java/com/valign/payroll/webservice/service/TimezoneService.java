package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;
import com.valign.payroll.webservice.model.Timezone;;

public interface TimezoneService {

	Timezone create(Timezone timezone);

	Timezone delete(int id);

	Page<Timezone> findAllSortOnColumn(int localeId, String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<Timezone> findAllFilter(int localeId,String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	Timezone findById(int id);

	Timezone update(Timezone timezone);

	public java.util.List<Timezone> findAllByLocaleId(int localeId);
	
	public Integer countRecordByLocaleId(int localeId);
}