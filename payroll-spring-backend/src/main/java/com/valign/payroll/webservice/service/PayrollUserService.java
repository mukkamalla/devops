package com.valign.payroll.webservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.PayrollUser;
import com.valign.payroll.webservice.util.SearchCriteria;

public interface PayrollUserService {

	PayrollUser create(PayrollUser user, String authtoken);
	PayrollUser createNewSubscription(PayrollUser user, String authtoken);

	PayrollUser delete(int id);

	Page<PayrollUser> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<PayrollUser> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc,
			String filter);

	PayrollUser findById(int id);
	java.util.List<PayrollUser> findByUserId(String userId);

	PayrollUser update(PayrollUser user);

}
