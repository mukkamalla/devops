package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.CompanyGroup;
import com.valign.payroll.webservice.model.CompanyGroupList;
import com.valign.payroll.webservice.service.CompanyGroupService;
import com.valign.payroll.webservice.model.CompanyGroupCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class CompanyGroupController {

	@Autowired
	private CompanyGroupService companyGroupService;

	@PostMapping(path = { "/api/companyGroups" })
	public ResponseEntity<String> create(@RequestBody String localeInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		CompanyGroup companyGroup = null;
		try {
			companyGroup = mapper.readValue(localeInputRequest, CompanyGroup.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		companyGroupService.create(companyGroup);
		ResponseEntity<String> response = new ResponseEntity<String>("CompanyGroup Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/companyGroups/{id}" })
	public ResponseEntity<CompanyGroup> findById(@PathVariable("id") int id) {

		CompanyGroup companyGroup = companyGroupService.findById(id);

		if (companyGroup == null) {

			return new ResponseEntity<CompanyGroup>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<CompanyGroup>(companyGroup, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/companyGroups/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String localeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		CompanyGroup companyGroup = null;
		CompanyGroup companyGroup1 = companyGroupService.findById(id);

		try {
			companyGroup = mapper.readValue(localeInputRequest, CompanyGroup.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		companyGroup.setUpdatedDate(new java.util.Date());

		companyGroupService.update(companyGroup);
		ResponseEntity<String> response = new ResponseEntity<String>("CompanyGroup Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/companyGroups/{id}" })
	public CompanyGroup delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return companyGroupService.delete(id);
	}

	@GetMapping(path = { "/api/open/companyGroups/findAll" })
	public CompanyGroupList findAll() {

		
		java.util.List<CompanyGroup> companyGroupList = companyGroupService.findAll();
		
		CompanyGroupList finalCompanyGroupList = new CompanyGroupList(companyGroupList);
		return finalCompanyGroupList;
	}

	@GetMapping(path = { "/api/companyGroups/findAll" })
	public CompanyGroupList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<CompanyGroup> companyGroupList = null;
		//java.util.List<CompanyGroup> companyGroupList1 = new java.util.ArrayList<CompanyGroup>();
		if (filter.length() > 0) {
			companyGroupList = companyGroupService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			companyGroupList = companyGroupService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		CompanyGroupList finalCompanyGroupList = new CompanyGroupList(companyGroupList);

		return finalCompanyGroupList;
	}

	@GetMapping(path = { "/api/companyGroups/recordCount" })
	public CompanyGroupCount findAll( HttpServletResponse response) {
		
		CompanyGroupCount companyGroupCount = null;
		Integer countValue = companyGroupService.countRecord();
		companyGroupCount = new CompanyGroupCount(countValue);

		return companyGroupCount;
	}
}