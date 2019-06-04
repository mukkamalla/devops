package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lt")
public class LeaveType {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String leaveTypeName;
	@Column
	private Integer leavePaid;  // FK with table yn
	@Column
	private Double numberOfDayPermissiblePerYear;  
	@Column
	private Double numberOfDayPermissiblePerMonth;  
	@Column
	private Integer allowedToCarryForward;  // FK with table yn
	@Column
	private Double carryForwardLimit;
	@Column
	private Integer allowedToEncash;  // FK with table yn
	@Column
	private Double encashLimit;
	
	@Column
	private Integer companyId; // FK with companies table
	@Column
	private Integer locationId; // FK with loc table
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
	
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public Integer getLeavePaid() {
		return leavePaid;
	}
	public void setLeavePaid(Integer leavePaid) {
		this.leavePaid = leavePaid;
	}
	public Double getNumberOfDayPermissiblePerYear() {
		return numberOfDayPermissiblePerYear;
	}
	public void setNumberOfDayPermissiblePerYear(Double numberOfDayPermissiblePerYear) {
		this.numberOfDayPermissiblePerYear = numberOfDayPermissiblePerYear;
	}
	public Double getNumberOfDayPermissiblePerMonth() {
		return numberOfDayPermissiblePerMonth;
	}
	public void setNumberOfDayPermissiblePerMonth(Double numberOfDayPermissiblePerMonth) {
		this.numberOfDayPermissiblePerMonth = numberOfDayPermissiblePerMonth;
	}
	public Integer getAllowedToCarryForward() {
		return allowedToCarryForward;
	}
	public void setAllowedToCarryForward(Integer allowedToCarryForward) {
		this.allowedToCarryForward = allowedToCarryForward;
	}
	public Double getCarryForwardLimit() {
		return carryForwardLimit;
	}
	public void setCarryForwardLimit(Double carryForwardLimit) {
		this.carryForwardLimit = carryForwardLimit;
	}
	public Integer getAllowedToEncash() {
		return allowedToEncash;
	}
	public void setAllowedToEncash(Integer allowedToEncash) {
		this.allowedToEncash = allowedToEncash;
	}
	public Double getEncashLimit() {
		return encashLimit;
	}
	public void setEncashLimit(Double encashLimit) {
		this.encashLimit = encashLimit;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	
}