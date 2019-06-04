package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Designation;
import com.valign.payroll.webservice.model.DesignationList;
import com.valign.payroll.webservice.service.DesignationService;
import com.valign.payroll.webservice.model.DesignationCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class DesignationController {

	@Autowired
	private DesignationService designationService;

	@PostMapping(path = { "/api/designations" })
	public ResponseEntity<String> create(@RequestBody String designationInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Designation designation = null;
		try {
			designation = mapper.readValue(designationInputRequest, Designation.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		designationService.create(designation);
		ResponseEntity<String> response = new ResponseEntity<String>("Designation Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/designations/{id}" })
	public ResponseEntity<Designation> findById(@PathVariable("id") int id) {

		Designation designation = designationService.findById(id);

		if (designation == null) {

			return new ResponseEntity<Designation>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Designation>(designation, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/designations/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String designationInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Designation designation = null;

		try {
			designation = mapper.readValue(designationInputRequest, Designation.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		designation.setUpdatedDate(new java.util.Date());

		designationService.update(designation);
		ResponseEntity<String> response = new ResponseEntity<String>("Designation Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/designations/{id}" })
	public Designation delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return designationService.delete(id);
	}

	@GetMapping(path = { "/api/open/designations/findAll" })
	public DesignationList findAll() {

		java.util.List<Designation> designationList = designationService.findAll();

		DesignationList finalDesignationList = new DesignationList(designationList);
		return finalDesignationList;
	}

	@GetMapping(path = { "/api/designations/findAll" })
	public DesignationList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<Designation> designationList = null;

		if (filter.length() > 0) {
			designationList = designationService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			designationList = designationService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		DesignationList finalDesignationList = new DesignationList(designationList);

		return finalDesignationList;
	}

	@GetMapping(path = { "/api/designations/recordCount" })
	public DesignationCount findAll(HttpServletResponse response) {

		DesignationCount designationCount = null;
		Integer countValue = designationService.countRecord();
		designationCount = new DesignationCount(countValue);

		return designationCount;
	}
}