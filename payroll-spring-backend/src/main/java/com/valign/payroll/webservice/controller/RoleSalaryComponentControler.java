package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.RoleSalaryComponent;
import com.valign.payroll.webservice.model.RoleSalaryComponentList;
import com.valign.payroll.webservice.service.RoleSalaryComponentService;
import com.valign.payroll.webservice.model.GradeCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class RoleSalaryComponentControler {

	@Autowired
	private RoleSalaryComponentService roleSalaryComponentService;

	@PostMapping(path = { "/api/roleSalaryComponents" })
	public ResponseEntity<String> create(@RequestBody String roleSalaryComponentInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RoleSalaryComponent roleSalaryComponent = null;
		try {
			roleSalaryComponent = mapper.readValue(roleSalaryComponentInputRequest, RoleSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		roleSalaryComponentService.create(roleSalaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("RoleSalaryComponent Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/roleSalaryComponents/{id}" })
	public ResponseEntity<RoleSalaryComponent> findById(@PathVariable("id") int id) {

		RoleSalaryComponent roleSalaryComponent = roleSalaryComponentService.findById(id);

		if (roleSalaryComponent == null) {

			return new ResponseEntity<RoleSalaryComponent>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<RoleSalaryComponent>(roleSalaryComponent, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/roleSalaryComponents/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String roleSalaryComponentInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RoleSalaryComponent roleSalaryComponent = null;

		try {
			roleSalaryComponent = mapper.readValue(roleSalaryComponentInputRequest, RoleSalaryComponent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		roleSalaryComponent.setUpdatedDate(new java.util.Date());

		roleSalaryComponentService.update(roleSalaryComponent);
		ResponseEntity<String> response = new ResponseEntity<String>("RoleSalaryComponent Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/roleSalaryComponents/{id}" })
	public RoleSalaryComponent delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return roleSalaryComponentService.delete(id);
	}

	@GetMapping(path = { "/api/open/roleSalaryComponents/findAll" })
	public RoleSalaryComponentList findAll() {

		java.util.List<RoleSalaryComponent> roleSalaryComponentList = roleSalaryComponentService.findAll();

		RoleSalaryComponentList finalRoleSalaryComponentList = new RoleSalaryComponentList(roleSalaryComponentList);
		return finalRoleSalaryComponentList;
	}

	@GetMapping(path = { "/api/roleSalaryComponents/findAll" })
	public RoleSalaryComponentList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<RoleSalaryComponent> roleSalaryComponentList = null;

		if (filter.length() > 0) {
			roleSalaryComponentList = roleSalaryComponentService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			roleSalaryComponentList = roleSalaryComponentService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		RoleSalaryComponentList finalRoleSalaryComponentList = new RoleSalaryComponentList(roleSalaryComponentList);

		return finalRoleSalaryComponentList;
	}

	@GetMapping(path = { "/api/roleSalaryComponents/recordCount" })
	public GradeCount findAll(HttpServletResponse response) {

		GradeCount roleSalaryComponentCount = null;
		Integer countValue = roleSalaryComponentService.countRecord();
		roleSalaryComponentCount = new GradeCount(countValue);

		return roleSalaryComponentCount;
	}
}