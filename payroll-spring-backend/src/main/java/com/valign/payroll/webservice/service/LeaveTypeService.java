package com.valign.payroll.webservice.service;

import org.springframework.data.domain.Page;

import com.valign.payroll.webservice.model.LeaveType;

public interface LeaveTypeService {

	LeaveType create(LeaveType leaveType);

	LeaveType delete(int id);

	Page<LeaveType> findAllSortOnColumn(String pageSize, String pageNumber, String columnName, String asc_desc);

	Page<LeaveType> findAllFilter(String pageSize, String pageNumber, String columnName, String asc_desc, String filter);

	LeaveType findById(int id);

	LeaveType update(LeaveType leaveType);

	public java.util.List<LeaveType> findAll();

	public Integer countRecord();
}