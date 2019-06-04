package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.EmployeeSalaryComponent;
import com.valign.payroll.webservice.model.EmployeeSalaryComponentList;
import com.valign.payroll.webservice.service.EmployeeSalaryComponentService;
import com.valign.payroll.webservice.model.EmployeeSalaryComponentCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class EmployeeSalaryComponentController {

	@Autowired
	private EmployeeSalaryComponentService employeeSalaryComponentService;

	@PostMapping(path = { "/api/employeeSalaryComponents" })
	public ResponseEntity<String> create(@RequestBody String employeeSalaryComponentInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmployeeSalaryComponent employeeSalaryComponent = null;
		try {
			employeeSalaryComponent = mapper.readValue(employeeSalaryComponentInputRequest, EmployeeSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		employeeSalaryComponentService.create(employeeSalaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("EmployeeSalaryComponent Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/employeeSalaryComponents/{id}" })
	public ResponseEntity<EmployeeSalaryComponent> findById(@PathVariable("id") int id) {

		EmployeeSalaryComponent employeeSalaryComponent = employeeSalaryComponentService.findById(id);

		if (employeeSalaryComponent == null) {

			return new ResponseEntity<EmployeeSalaryComponent>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<EmployeeSalaryComponent>(employeeSalaryComponent, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/employeeSalaryComponents/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String employeeSalaryComponentInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmployeeSalaryComponent employeeSalaryComponent = null;
		

		try {
			employeeSalaryComponent = mapper.readValue(employeeSalaryComponentInputRequest, EmployeeSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		employeeSalaryComponent.setUpdatedDate(new java.util.Date());

		employeeSalaryComponentService.update(employeeSalaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("EmployeeSalaryComponent Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/employeeSalaryComponents/{id}" })
	public EmployeeSalaryComponent delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return employeeSalaryComponentService.delete(id);
	}

	@GetMapping(path = { "/api/open/employeeSalaryComponents/findAll" })
	public EmployeeSalaryComponentList findAll() {

		
		java.util.List<EmployeeSalaryComponent> employeeSalaryComponentList = employeeSalaryComponentService.findAll();
		
		EmployeeSalaryComponentList finalEmployeeSalaryComponentList = new EmployeeSalaryComponentList(employeeSalaryComponentList);
		return finalEmployeeSalaryComponentList;
	}

	@GetMapping(path = { "/api/employeeSalaryComponents/findAll" })
	public EmployeeSalaryComponentList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<EmployeeSalaryComponent> employeeSalaryComponentList = null;
		
		if (filter.length() > 0) {
			employeeSalaryComponentList = employeeSalaryComponentService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			employeeSalaryComponentList = employeeSalaryComponentService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		EmployeeSalaryComponentList finalEmployeeSalaryComponentList = new EmployeeSalaryComponentList(employeeSalaryComponentList);

		return finalEmployeeSalaryComponentList;
	}

	@GetMapping(path = { "/api/employeeSalaryComponents/recordCount" })
	public EmployeeSalaryComponentCount findAll( HttpServletResponse response) {
		
		EmployeeSalaryComponentCount employeeSalaryComponentCount = null;
		Integer countValue = employeeSalaryComponentService.countRecord();
		employeeSalaryComponentCount = new EmployeeSalaryComponentCount(countValue);

		return employeeSalaryComponentCount;
	}
}