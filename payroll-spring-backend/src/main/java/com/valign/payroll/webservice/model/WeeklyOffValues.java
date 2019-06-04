package com.valign.payroll.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wov")
public class WeeklyOffValues {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer weeklyOffId;  // FK with table Weekly Offs (wofs table)
	@Column
	private String weeklyOffName; // values are 2nd Saturday , 4th Saturday
	@Column
	private Integer weekValueId; // FK with week_values table, value of 1 means 1st Week, value of 2 means 2nd
								// week..
	@Column
	private Integer dayValueId; // FK with day_values table, value of 1 means Monday, 2 means Tuesday
	@Column
	private Integer companyId; // FK with companies table
	@Column
	private Integer localeId; // FK with localeId table primary key
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

	public String getWeeklyOffName() {
		return weeklyOffName;
	}

	public void setWeeklyOffName(String weeklyOffName) {
		this.weeklyOffName = weeklyOffName;
	}

	public Integer getWeekValueId() {
		return weekValueId;
	}

	public void setWeekValueId(Integer weekValueId) {
		this.weekValueId = weekValueId;
	}

	public Integer getDayValueId() {
		return dayValueId;
	}

	public void setDayValueId(Integer dayValueId) {
		this.dayValueId = dayValueId;
	}

	
	public Integer getLocaleId() {
		return localeId;
	}

	public void setLocaleId(Integer localeId) {
		this.localeId = localeId;
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

	public Integer getWeeklyOffId() {
		return weeklyOffId;
	}

	public void setWeeklyOffId(Integer weeklyOffId) {
		this.weeklyOffId = weeklyOffId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}