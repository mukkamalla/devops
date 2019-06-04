package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Company;
import com.valign.payroll.webservice.model.CompanyList;
import com.valign.payroll.webservice.service.CompanyService;
import com.valign.payroll.webservice.model.CompanyCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping(path = { "/api/companies" })
	public ResponseEntity<String> create(@RequestBody String companyInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Company company = null;
		try {
			company = mapper.readValue(companyInputRequest, Company.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		companyService.create(company);
		ResponseEntity<String> response = new ResponseEntity<String>("Company Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/companies/{id}" })
	public ResponseEntity<Company> findById(@PathVariable("id") int id) {

		Company company = companyService.findById(id);

		if (company == null) {

			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Company>(company, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/companies/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String companyInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Company company = null;
		

		try {
			company = mapper.readValue(companyInputRequest, Company.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		company.setUpdatedDate(new java.util.Date());

		companyService.update(company);
		ResponseEntity<String> response = new ResponseEntity<String>("Company Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/companies/{id}" })
	public Company delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return companyService.delete(id);
	}

	@GetMapping(path = { "/api/open/companies/findAll" })
	public CompanyList findAll() {

		
		java.util.List<Company> companyList = companyService.findAll();
		
		CompanyList finalCompanyList = new CompanyList(companyList);
		return finalCompanyList;
	}

	@GetMapping(path = { "/api/companies/findAll" })
	public CompanyList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Company> companyList = null;
		
		if (filter.length() > 0) {
			companyList = companyService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			companyList = companyService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		CompanyList finalCompanyList = new CompanyList(companyList);

		return finalCompanyList;
	}

	@GetMapping(path = { "/api/companies/recordCount" })
	public CompanyCount findAll( HttpServletResponse response) {
		
		CompanyCount companyCount = null;
		Integer countValue = companyService.countRecord();
		companyCount = new CompanyCount(countValue);

		return companyCount;
	}
}