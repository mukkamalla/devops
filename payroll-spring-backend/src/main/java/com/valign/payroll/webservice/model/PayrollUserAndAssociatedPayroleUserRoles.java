package com.valign.payroll.webservice.model;

import javax.persistence.*;

@Entity
@Table(name = "puar")
public class PayrollUserAndAssociatedPayroleUserRoles {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer recordStatus; //FK with record status table
	@Column
	private Integer payrollUserId; // FK with payroll_user table
	@Column
	private Integer payrollUserRoleId; // FK with payroll_user_roll table
	@Column
	private Integer companyId; // FK with companies table
	
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
	public Integer getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Integer getPayrollUserId() {
		return payrollUserId;
	}
	public void setPayrollUserId(Integer payrollUserId) {
		this.payrollUserId = payrollUserId;
	}
	public Integer getPayrollUserRoleId() {
		return payrollUserRoleId;
	}
	public void setPayrollUserRoleId(Integer payrollUserRoleId) {
		this.payrollUserRoleId = payrollUserRoleId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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