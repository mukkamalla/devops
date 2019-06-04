package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.PayrollUserRole;
import com.valign.payroll.webservice.model.PayrollUserRoleList;
import com.valign.payroll.webservice.service.PayrollUserRoleService;
import com.valign.payroll.webservice.model.PayrollUserRoleCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class PayrollUserRoleController {

	@Autowired
	private PayrollUserRoleService payrollUserRoleService;

	@PostMapping(path = { "/api/payrollUserRoles" })
	public ResponseEntity<String> create(@RequestBody String payrollUserRoleInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		PayrollUserRole payrollUserRole = null;
		try {
			payrollUserRole = mapper.readValue(payrollUserRoleInputRequest, PayrollUserRole.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		payrollUserRoleService.create(payrollUserRole);
		ResponseEntity<String> response = new ResponseEntity<String>("PayrollUserRole Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/payrollUserRoles/{id}" })
	public ResponseEntity<PayrollUserRole> findById(@PathVariable("id") int id) {

		PayrollUserRole payrollUserRole = payrollUserRoleService.findById(id);

		if (payrollUserRole == null) {

			return new ResponseEntity<PayrollUserRole>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<PayrollUserRole>(payrollUserRole, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/payrollUserRoles/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String payrollUserRoleInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		PayrollUserRole payrollUserRole = null;

		try {
			payrollUserRole = mapper.readValue(payrollUserRoleInputRequest, PayrollUserRole.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		payrollUserRole.setUpdatedDate(new java.util.Date());

		payrollUserRoleService.update(payrollUserRole);
		ResponseEntity<String> response = new ResponseEntity<String>("PayrollUserRole Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/payrollUserRoles/{id}" })
	public PayrollUserRole delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return payrollUserRoleService.delete(id);
	}

	@GetMapping(path = { "/api/open/payrollUserRoles/findAll" })
	public PayrollUserRoleList findAll() {

		java.util.List<PayrollUserRole> gradeList = payrollUserRoleService.findAll();

		PayrollUserRoleList finalPayrollUserRoleList = new PayrollUserRoleList(gradeList);
		return finalPayrollUserRoleList;
	}

	@GetMapping(path = { "/api/payrollUserRoles/findAll" })
	public PayrollUserRoleList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<PayrollUserRole> gradeList = null;

		if (filter.length() > 0) {
			gradeList = payrollUserRoleService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			gradeList = payrollUserRoleService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		PayrollUserRoleList finalPayrollUserRoleList = new PayrollUserRoleList(gradeList);

		return finalPayrollUserRoleList;
	}

	@GetMapping(path = { "/api/payrollUserRoles/recordCount" })
	public PayrollUserRoleCount findAll(HttpServletResponse response) {

		PayrollUserRoleCount payrollUserRoleCount = null;
		Integer countValue = payrollUserRoleService.countRecord();
		payrollUserRoleCount = new PayrollUserRoleCount(countValue);

		return payrollUserRoleCount;
	}
}