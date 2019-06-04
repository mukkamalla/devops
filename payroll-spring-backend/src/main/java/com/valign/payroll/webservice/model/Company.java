package com.valign.payroll.webservice.model;

import javax.persistence.*;

@Entity
@Table(name = "comp")
public class Company {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String companyName;
	@Column
	private String keyContactName;
	@Column
	private String keyContactPhone;
	@Column
	private String keyContactEmailId;
	@Column
	private Integer timeZoneId; // FK with timezones table
	@Column
	private Integer baseCurrencyId; // FK with currencies table
	@Column
	private Integer companyGroupId; // FK with company_group table
	@Column
	private java.util.Date financialYearStart;
	@Column
	private Integer subscriptionLicenseCount;
	@Column
	private String address1;
	@Column
	private String address2;
	@Column
	private Integer countryId;
	@Column
	private String state;
	@Column
	private String city;
	@Column
	private String postalCode;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getKeyContactName() {
		return keyContactName;
	}

	public void setKeyContactName(String keyContactName) {
		this.keyContactName = keyContactName;
	}

	public String getKeyContactPhone() {
		return keyContactPhone;
	}

	public void setKeyContactPhone(String keyContactPhone) {
		this.keyContactPhone = keyContactPhone;
	}

	public String getKeyContactEmailId() {
		return keyContactEmailId;
	}

	public void setKeyContactEmailId(String keyContactEmailId) {
		this.keyContactEmailId = keyContactEmailId;
	}

	public Integer getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(Integer timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public Integer getBaseCurrencyId() {
		return baseCurrencyId;
	}

	public void setBaseCurrencyId(Integer baseCurrencyId) {
		this.baseCurrencyId = baseCurrencyId;
	}

	public Integer getCompanyGroupId() {
		return companyGroupId;
	}

	public void setCompanyGroupId(Integer companyGroupId) {
		this.companyGroupId = companyGroupId;
	}

	public java.util.Date getFinancialYearStart() {
		return financialYearStart;
	}

	public void setFinancialYearStart(java.util.Date financialYearStart) {
		this.financialYearStart = financialYearStart;
	}

	public Integer getSubscriptionLicenseCount() {
		return subscriptionLicenseCount;
	}

	public void setSubscriptionLicenseCount(Integer subscriptionLicenseCount) {
		this.subscriptionLicenseCount = subscriptionLicenseCount;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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