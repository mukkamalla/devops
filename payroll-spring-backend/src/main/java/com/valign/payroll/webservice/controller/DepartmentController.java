package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Department;
import com.valign.payroll.webservice.model.DepartmentList;
import com.valign.payroll.webservice.service.DepartmentService;
import com.valign.payroll.webservice.model.DepartmentCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping(path = { "/api/departments" })
	public ResponseEntity<String> create(@RequestBody String departmentInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Department department = null;
		try {
			department = mapper.readValue(departmentInputRequest, Department.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		departmentService.create(department);
		ResponseEntity<String> response = new ResponseEntity<String>("Department Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/departments/{id}" })
	public ResponseEntity<Department> findById(@PathVariable("id") int id) {

		Department department = departmentService.findById(id);

		if (department == null) {

			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/departments/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String departmentInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Department department = null;
		Department department1 = departmentService.findById(id);

		try {
			department = mapper.readValue(departmentInputRequest, Department.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		department.setUpdatedDate(new java.util.Date());

		departmentService.update(department);
		ResponseEntity<String> response = new ResponseEntity<String>("Department Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/departments/{id}" })
	public Department delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return departmentService.delete(id);
	}

	@GetMapping(path = { "/api/open/departments/findAll" })
	public DepartmentList findAll() {

		
		java.util.List<Department> departmentList = departmentService.findAll();
		
		DepartmentList finalDepartmentList = new DepartmentList(departmentList);
		return finalDepartmentList;
	}

	@GetMapping(path = { "/api/departments/findAll" })
	public DepartmentList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Department> departmentList = null;
		
		if (filter.length() > 0) {
			departmentList = departmentService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			departmentList = departmentService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		DepartmentList finalDepartmentList = new DepartmentList(departmentList);

		return finalDepartmentList;
	}

	@GetMapping(path = { "/api/departments/recordCount" })
	public DepartmentCount findAll( HttpServletResponse response) {
		
		DepartmentCount departmentCount = null;
		Integer countValue = departmentService.countRecord();
		departmentCount = new DepartmentCount(countValue);

		return departmentCount;
	}
}