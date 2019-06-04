package com.valign.payroll.webservice.model;

import javax.persistence.*;

@Entity
@Table(name = "cg")
public class CompanyGroup {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String companyGroupName;
	@Column
	private String companyGroupDesc;
	@Column
	private Integer recordStatus; // FK with record_statues table
	@Column
	private Integer companyCount;  // default trail edition value is 1, one super admin per company.
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

	public String getCompanyGroupName() {
		return companyGroupName;
	}

	public void setCompanyGroupName(String companyGroupName) {
		this.companyGroupName = companyGroupName;
	}

	public String getCompanyGroupDesc() {
		return companyGroupDesc;
	}

	public void setCompanyGroupDesc(String companyGroupDesc) {
		this.companyGroupDesc = companyGroupDesc;
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

	public Integer getCompanyCount() {
		return companyCount;
	}

	public void setCompanyCount(Integer companyCount) {
		this.companyCount = companyCount;
	}

	
}