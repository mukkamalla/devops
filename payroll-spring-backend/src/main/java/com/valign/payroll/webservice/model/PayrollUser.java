		package com.valign.payroll.webservice.model;

import javax.persistence.*;

@Entity
@Table(name = "pu")
public class PayrollUser {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String emailId;
	@Column
	private String avatar;
	@Column
	private String userId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String password;
	@Column
	private String salt;
	@Column
	private Integer recordStatus; //FK with record status table
	@Column
	private Integer payrollUserRole; // FK with payroll_user_roll table
	@Column
	private Integer companyId; // FK with companies table
	@Column
	private Integer employeeId; // FK with employees table
	@Column
	private Integer defaultLocaleId; // FK with locale table

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getPayrollUserRole() {
		return payrollUserRole;
	}

	public void setPayrollUserRole(Integer payrollUserRole) {
		this.payrollUserRole = payrollUserRole;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	

	public Integer getDefaultLocaleId() {
		return defaultLocaleId;
	}

	public void setDefaultLocaleId(Integer defaultLocaleId) {
		this.defaultLocaleId = defaultLocaleId;
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