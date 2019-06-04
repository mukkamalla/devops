package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empsf")
public class EmployeeShift {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer employeeId; // FK with employees table
	@Column
	private Integer shiftId; // FK with shifts table
	@Column
	private Integer companyId; // FK with companies table
	@Column
	private java.util.Date shiftStartDate;
	@Column
	private java.util.Date shiftEndDate;
	@Column
	private Integer recordStatus; // FK with record_statues table

	@Column
	private java.util.Date creationDate;
	@Column
	private Integer creationBy; // FK with payroll_users table
	@Column
	private java.util.Date updatedDate;
	@Column
	private Integer updatedBy; // FK with payroll_users table
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getShiftId() {
		return shiftId;
	}
	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public java.util.Date getShiftStartDate() {
		return shiftStartDate;
	}
	public void setShiftStartDate(java.util.Date shiftStartDate) {
		this.shiftStartDate = shiftStartDate;
	}
	public java.util.Date getShiftEndDate() {
		return shiftEndDate;
	}
	public void setShiftEndDate(java.util.Date shiftEndDate) {
		this.shiftEndDate = shiftEndDate;
	}
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public java.util.Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getCreationBy() {
		return creationBy;
	}
	public void setCreationBy(Integer creationBy) {
		this.creationBy = creationBy;
	}
	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
}