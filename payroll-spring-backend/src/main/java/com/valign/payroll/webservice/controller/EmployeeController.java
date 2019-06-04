package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Employee;
import com.valign.payroll.webservice.model.EmployeeList;
import com.valign.payroll.webservice.service.EmployeeService;
import com.valign.payroll.webservice.model.EmployeeCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(path = { "/api/employees" })
	public ResponseEntity<String> create(@RequestBody String employeeInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Employee employee = null;
		try {
			employee = mapper.readValue(employeeInputRequest, Employee.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		employeeService.create(employee);
		ResponseEntity<String> response = new ResponseEntity<String>("Employee Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/employees/{id}" })
	public ResponseEntity<Employee> findById(@PathVariable("id") int id) {

		Employee employee = employeeService.findById(id);

		if (employee == null) {

			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/employees/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String employeeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Employee employee = null;
		

		try {
			employee = mapper.readValue(employeeInputRequest, Employee.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		employee.setUpdatedDate(new java.util.Date());

		employeeService.update(employee);
		ResponseEntity<String> response = new ResponseEntity<String>("Employee Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/employees/{id}" })
	public Employee delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return employeeService.delete(id);
	}

	@GetMapping(path = { "/api/open/employees/findAll" })
	public EmployeeList findAll() {

		
		java.util.List<Employee> employeeList = employeeService.findAll();
		
		EmployeeList finalEmployeeList = new EmployeeList(employeeList);
		return finalEmployeeList;
	}

	@GetMapping(path = { "/api/employees/findAll" })
	public EmployeeList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Employee> employeeList = null;
		
		if (filter.length() > 0) {
			employeeList = employeeService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			employeeList = employeeService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		EmployeeList finalEmployeeList = new EmployeeList(employeeList);

		return finalEmployeeList;
	}

	@GetMapping(path = { "/api/employees/recordCount" })
	public EmployeeCount findAll( HttpServletResponse response) {
		
		EmployeeCount employeeCount = null;
		Integer countValue = employeeService.countRecord();
		employeeCount = new EmployeeCount(countValue);

		return employeeCount;
	}
}