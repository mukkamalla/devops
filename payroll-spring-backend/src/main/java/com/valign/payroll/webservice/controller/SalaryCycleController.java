package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.SalaryCycle;
import com.valign.payroll.webservice.model.SalaryCycleList;
import com.valign.payroll.webservice.service.SalaryCycleService;
import com.valign.payroll.webservice.model.SalaryCycleCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class SalaryCycleController {

	@Autowired
	private SalaryCycleService salaryCycleService;

	@PostMapping(path = { "/api/salaryCycles" })
	public ResponseEntity<String> create(@RequestBody String salaryCycleInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		SalaryCycle salaryCycle = null;
		try {
			salaryCycle = mapper.readValue(salaryCycleInputRequest, SalaryCycle.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		salaryCycleService.create(salaryCycle);
		ResponseEntity<String> response = new ResponseEntity<String>("SalaryCycle Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/salaryCycles/{id}" })
	public ResponseEntity<SalaryCycle> findById(@PathVariable("id") int id) {

		SalaryCycle salaryCycle = salaryCycleService.findById(id);

		if (salaryCycle == null) {

			return new ResponseEntity<SalaryCycle>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<SalaryCycle>(salaryCycle, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/salaryCycles/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String salaryCycleInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		SalaryCycle salaryCycle = null;
		

		try {
			salaryCycle = mapper.readValue(salaryCycleInputRequest, SalaryCycle.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		salaryCycle.setUpdatedDate(new java.util.Date());

		salaryCycleService.update(salaryCycle);
		ResponseEntity<String> response = new ResponseEntity<String>("SalaryCycle Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/salaryCycles/{id}" })
	public SalaryCycle delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return salaryCycleService.delete(id);
	}

	@GetMapping(path = { "/api/open/salaryCycles/findAll" })
	public SalaryCycleList findAll() {

		
		java.util.List<SalaryCycle> salaryCycleList = salaryCycleService.findAll();
		
		SalaryCycleList finalSalaryCycleList = new SalaryCycleList(salaryCycleList);
		return finalSalaryCycleList;
	}

	@GetMapping(path = { "/api/salaryCycles/findAll" })
	public SalaryCycleList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<SalaryCycle> salaryCycleList = null;
		
		if (filter.length() > 0) {
			salaryCycleList = salaryCycleService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			salaryCycleList = salaryCycleService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		SalaryCycleList finalSalaryCycleList = new SalaryCycleList(salaryCycleList);

		return finalSalaryCycleList;
	}

	@GetMapping(path = { "/api/salaryCycles/recordCount" })
	public SalaryCycleCount findAll( HttpServletResponse response) {
		
		SalaryCycleCount localeCount = null;
		Integer countValue = salaryCycleService.countRecord();
		localeCount = new SalaryCycleCount(countValue);

		return localeCount;
	}
}