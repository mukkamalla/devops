package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.CompanyLevelSalaryComponent;
import com.valign.payroll.webservice.model.CompanyLevelSalaryComponentList;
import com.valign.payroll.webservice.service.CompanyLevelSalaryComponentService;
import com.valign.payroll.webservice.model.CompanyLevelSalaryComponentCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class CompanyLevelSalaryComponentController {

	@Autowired
	private CompanyLevelSalaryComponentService salaryComponentService;

	@PostMapping(path = { "/api/salaryComponents" })
	public ResponseEntity<String> create(@RequestBody String salaryComponentRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		CompanyLevelSalaryComponent salaryComponent = null;
		try {
			salaryComponent = mapper.readValue(salaryComponentRequest, CompanyLevelSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		salaryComponentService.create(salaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("CompanyLevelSalaryComponent Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/salaryComponents/{id}" })
	public ResponseEntity<CompanyLevelSalaryComponent> findById(@PathVariable("id") int id) {

		CompanyLevelSalaryComponent salaryComponent = salaryComponentService.findById(id);

		if (salaryComponent == null) {

			return new ResponseEntity<CompanyLevelSalaryComponent>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<CompanyLevelSalaryComponent>(salaryComponent, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/salaryComponents/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String salaryComponentRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		CompanyLevelSalaryComponent salaryComponent = null;
		

		try {
			salaryComponent = mapper.readValue(salaryComponentRequest, CompanyLevelSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		salaryComponent.setUpdatedDate(new java.util.Date());

		salaryComponentService.update(salaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("CompanyLevelSalaryComponent Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/salaryComponents/{id}" })
	public CompanyLevelSalaryComponent delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return salaryComponentService.delete(id);
	}

	@GetMapping(path = { "/api/open/salaryComponents/findAll" })
	public CompanyLevelSalaryComponentList findAll() {

		
		java.util.List<CompanyLevelSalaryComponent> salaryComponentList = salaryComponentService.findAll();
		
		CompanyLevelSalaryComponentList finalSalaryComponentList = new CompanyLevelSalaryComponentList(salaryComponentList);
		return finalSalaryComponentList;
	}

	@GetMapping(path = { "/api/salaryComponents/findAll" })
	public CompanyLevelSalaryComponentList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<CompanyLevelSalaryComponent> salaryComponentList = null;
		
		if (filter.length() > 0) {
			salaryComponentList = salaryComponentService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			salaryComponentList = salaryComponentService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		CompanyLevelSalaryComponentList finalSalaryComponentList = new CompanyLevelSalaryComponentList(salaryComponentList);

		return finalSalaryComponentList;
	}

	@GetMapping(path = { "/api/salaryComponents/recordCount" })
	public CompanyLevelSalaryComponentCount findAll( HttpServletResponse response) {
		
		CompanyLevelSalaryComponentCount companyLevelSalaryComponentCount = null;
		Integer countValue = salaryComponentService.countRecord();
		companyLevelSalaryComponentCount = new CompanyLevelSalaryComponentCount(countValue);

		return companyLevelSalaryComponentCount;
	}
}