package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "esc")
public class EmployeeSalaryComponent {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String type;  // F for Flat, P for Percentage, C for Compute using formula
	@Column
	private Integer percentageOn;  // FK on the same table rsc
	@Column
	private Integer value; // in case of Flat it is value, in case of percentage it is the percent value
	@Column
	private Integer currencyId;  //FK with currrency table
	
	@Column
	private String formula;  // the acutal formula if type field value is C
	@Column
	private Integer employeeSalaryMasterId;  // FK on the same table rsm
	@Column
	private Integer companySalaryComponentId;  // FK on the same table sc
	@Column
	private Integer companyId;   // FK with company table
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getPercentageOn() {
		return percentageOn;
	}
	public void setPercentageOn(Integer percentageOn) {
		this.percentageOn = percentageOn;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public Integer getEmployeeSalaryMasterId() {
		return employeeSalaryMasterId;
	}
	public void setEmployeeSalaryMasterId(Integer employeeSalaryMasterId) {
		this.employeeSalaryMasterId = employeeSalaryMasterId;
	}
	public Integer getCompanySalaryComponentId() {
		return companySalaryComponentId;
	}
	public void setCompanySalaryComponentId(Integer companySalaryComponentId) {
		this.companySalaryComponentId = companySalaryComponentId;
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
	
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	
}