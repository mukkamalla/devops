package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.EmployeeSalaryMaster;
import com.valign.payroll.webservice.model.EmployeeSalaryMasterList;
import com.valign.payroll.webservice.service.EmployeeSalaryMasterService;
import com.valign.payroll.webservice.model.EmployeeSalaryMasterCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class EmployeeSalaryMasterController {

	@Autowired
	private EmployeeSalaryMasterService employeeSalaryMasterService;

	@PostMapping(path = { "/api/employeeSalaryMasters" })
	public ResponseEntity<String> create(@RequestBody String employeeSalaryMasterInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmployeeSalaryMaster grade = null;
		try {
			grade = mapper.readValue(employeeSalaryMasterInputRequest, EmployeeSalaryMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		employeeSalaryMasterService.create(grade);
		ResponseEntity<String> response = new ResponseEntity<String>("EmployeeSalaryMaster Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/employeeSalaryMasters/{id}" })
	public ResponseEntity<EmployeeSalaryMaster> findById(@PathVariable("id") int id) {

		EmployeeSalaryMaster grade = employeeSalaryMasterService.findById(id);

		if (grade == null) {

			return new ResponseEntity<EmployeeSalaryMaster>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<EmployeeSalaryMaster>(grade, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/employeeSalaryMasters/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String employeeSalaryMasterInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		EmployeeSalaryMaster grade = null;

		try {
			grade = mapper.readValue(employeeSalaryMasterInputRequest, EmployeeSalaryMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		grade.setUpdatedDate(new java.util.Date());

		employeeSalaryMasterService.update(grade);
		ResponseEntity<String> response = new ResponseEntity<String>("EmployeeSalaryMaster Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/employeeSalaryMasters/{id}" })
	public EmployeeSalaryMaster delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return employeeSalaryMasterService.delete(id);
	}

	@GetMapping(path = { "/api/open/employeeSalaryMasters/findAll" })
	public EmployeeSalaryMasterList findAll() {

		java.util.List<EmployeeSalaryMaster> employeeSalaryMasterList = employeeSalaryMasterService.findAll();

		EmployeeSalaryMasterList finalGradeList = new EmployeeSalaryMasterList(employeeSalaryMasterList);
		return finalGradeList;
	}

	@GetMapping(path = { "/api/employeeSalaryMasters/findAll" })
	public EmployeeSalaryMasterList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<EmployeeSalaryMaster> employeeSalaryMasterList = null;

		if (filter.length() > 0) {
			employeeSalaryMasterList = employeeSalaryMasterService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			employeeSalaryMasterList = employeeSalaryMasterService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		EmployeeSalaryMasterList finalGradeList = new EmployeeSalaryMasterList(employeeSalaryMasterList);

		return finalGradeList;
	}

	@GetMapping(path = { "/api/employeeSalaryMasters/recordCount" })
	public EmployeeSalaryMasterCount findAll(HttpServletResponse response) {

		EmployeeSalaryMasterCount gradeCount = null;
		Integer countValue = employeeSalaryMasterService.countRecord();
		gradeCount = new EmployeeSalaryMasterCount(countValue);

		return gradeCount;
	}
}