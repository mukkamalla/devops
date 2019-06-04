package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.EmploymentStatus;
import com.valign.payroll.webservice.model.EmploymentStatusCount;
import com.valign.payroll.webservice.model.EmploymentStatusList;
import com.valign.payroll.webservice.service.EmploymentStatusService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class EmploymentStatusController {

	@Autowired
	private EmploymentStatusService employmentStatusService;

	@PostMapping(path = { "/api/employmentStatuses" })
	public ResponseEntity<String> create(@RequestBody String employmentStatusInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmploymentStatus employmentStatus = null;
		try {
			employmentStatus = mapper.readValue(employmentStatusInputRequest, EmploymentStatus.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		employmentStatusService.create(employmentStatus);
		ResponseEntity<String> response = new ResponseEntity<String>("EmploymentStatus Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/employmentStatuses/{id}" })
	public ResponseEntity<EmploymentStatus> findById(@PathVariable("id") int id) {

		EmploymentStatus employmentStatus = employmentStatusService.findById(id);

		if (employmentStatus == null) {

			return new ResponseEntity<EmploymentStatus>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<EmploymentStatus>(employmentStatus, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/employmentStatuses/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String employmentStatusInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmploymentStatus employmentStatus = null;
		EmploymentStatus employmentStatus1 = employmentStatusService.findById(id);

		try {
			employmentStatus = mapper.readValue(employmentStatusInputRequest, EmploymentStatus.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		employmentStatus1.setEmpStatus(employmentStatus.getEmpStatus());
		employmentStatus1.setEmpStatusDesc(employmentStatus.getEmpStatusDesc());
		employmentStatus1.setCompanyId(employmentStatus.getCompanyId());
		employmentStatus1.setRecordStatus(employmentStatus.getRecordStatus());
		employmentStatus1.setLocaleId(employmentStatus.getLocaleId());

		employmentStatus1.setUpdatedDate(new java.util.Date());

		employmentStatusService.update(employmentStatus1);
		ResponseEntity<String> response = new ResponseEntity<String>("EmploymentStatus Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/employmentStatuses/{id}" })
	public EmploymentStatus delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return employmentStatusService.delete(id);
	}

	@GetMapping(path = { "/api/open/employmentStatuses/findAll/{localeId}" })
	public EmploymentStatusList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<EmploymentStatus> employmentStatusList = employmentStatusService.findAllByLocaleId(localeId);
		java.util.List<EmploymentStatus> countryList1 = new java.util.ArrayList<EmploymentStatus>();

		for (EmploymentStatus employmentStatus : employmentStatusList) {
			if (employmentStatus.getLocaleId() == localeId) {
				countryList1.add(employmentStatus);
			}
		}
		EmploymentStatusList finalCountryList = new EmploymentStatusList(countryList1);
		return finalCountryList;
	}

	@GetMapping(path = { "/api/employmentStatuses/findAll/{localeId}" })
	public EmploymentStatusList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<EmploymentStatus> employmentStatusList = null;
		java.util.List<EmploymentStatus> countryList1 = new java.util.ArrayList<EmploymentStatus>();
		if (filter.length() > 0) {
			employmentStatusList = employmentStatusService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			employmentStatusList = employmentStatusService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (EmploymentStatus employmentStatus : employmentStatusList) {
			if (employmentStatus.getLocaleId() == localeId) {
				countryList1.add(employmentStatus);
			}
		}
		EmploymentStatusList finalCountryList = new EmploymentStatusList(countryList1);

		return finalCountryList;
	}

	@GetMapping(path = { "/api/employmentStatuses/recordCount/{localeId}" })
	public EmploymentStatusCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		EmploymentStatusCount employmentStatusCount = null;
		Integer countValue = employmentStatusService.countRecordByLocaleId(localeId);
		employmentStatusCount = new EmploymentStatusCount(countValue);

		return employmentStatusCount;
	}
}