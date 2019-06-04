package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.BusinessUnit;
import com.valign.payroll.webservice.model.BusinessUnitList;
import com.valign.payroll.webservice.service.BusinessUnitService;
import com.valign.payroll.webservice.model.BusinessUnitCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class BusinessUnitsController {

	@Autowired
	private BusinessUnitService businessUnitsService;

	@PostMapping(path = { "/api/businessUnits" })
	public ResponseEntity<String> create(@RequestBody String businessUnitsInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		BusinessUnit businessUnits = null;
		try {
			businessUnits = mapper.readValue(businessUnitsInputRequest, BusinessUnit.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		businessUnitsService.create(businessUnits);
		ResponseEntity<String> response = new ResponseEntity<String>("BusinessUnit Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/businessUnits/{id}" })
	public ResponseEntity<BusinessUnit> findById(@PathVariable("id") int id) {

		BusinessUnit businessUnits = businessUnitsService.findById(id);

		if (businessUnits == null) {

			return new ResponseEntity<BusinessUnit>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<BusinessUnit>(businessUnits, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/businessUnits/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String businessUnitsInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		BusinessUnit businessUnits = null;
		
		try {
			businessUnits = mapper.readValue(businessUnitsInputRequest, BusinessUnit.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		businessUnits.setUpdatedDate(new java.util.Date());

		businessUnitsService.update(businessUnits);
		ResponseEntity<String> response = new ResponseEntity<String>("BusinessUnit Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/businessUnits/{id}" })
	public BusinessUnit delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return businessUnitsService.delete(id);
	}

	@GetMapping(path = { "/api/open/businessUnits/findAll" })
	public BusinessUnitList findAll() {

		
		java.util.List<BusinessUnit> businessUnitsList = businessUnitsService.findAll();
		
		BusinessUnitList finalBusinessUnitsList = new BusinessUnitList(businessUnitsList);
		return finalBusinessUnitsList;
	}

	@GetMapping(path = { "/api/businessUnits/findAll" })
	public BusinessUnitList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<BusinessUnit> businessUnitsList = null;
		
		if (filter.length() > 0) {
			businessUnitsList = businessUnitsService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			businessUnitsList = businessUnitsService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		BusinessUnitList finalBusinessUnitsList = new BusinessUnitList(businessUnitsList);

		return finalBusinessUnitsList;
	}

	@GetMapping(path = { "/api/businessUnits/recordCount" })
	public BusinessUnitCount findAll( HttpServletResponse response) {
		
		BusinessUnitCount businessUnitCount = null;
		Integer countValue = businessUnitsService.countRecord();
		businessUnitCount = new BusinessUnitCount(countValue);

		return businessUnitCount;
	}
}