package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.RoleSalaryMaster;
import com.valign.payroll.webservice.model.RoleSalaryMasterList;
import com.valign.payroll.webservice.service.RoleSalaryMasterService;
import com.valign.payroll.webservice.model.RoleSalaryMasterCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class RoleSalaryMasterController {

	@Autowired
	private RoleSalaryMasterService roleSalaryMasterService;

	@PostMapping(path = { "/api/roleSalaryMasters" })
	public ResponseEntity<String> create(@RequestBody String roleSalaryMasterInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RoleSalaryMaster roleSalaryMaster = null;
		try {
			roleSalaryMaster = mapper.readValue(roleSalaryMasterInputRequest, RoleSalaryMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		roleSalaryMasterService.create(roleSalaryMaster);
		ResponseEntity<String> response = new ResponseEntity<String>("RoleSalaryMaster Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/roleSalaryMasters/{id}" })
	public ResponseEntity<RoleSalaryMaster> findById(@PathVariable("id") int id) {

		RoleSalaryMaster roleSalaryMaster = roleSalaryMasterService.findById(id);

		if (roleSalaryMaster == null) {

			return new ResponseEntity<RoleSalaryMaster>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<RoleSalaryMaster>(roleSalaryMaster, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/roleSalaryMasters/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String roleSalaryMasterInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RoleSalaryMaster roleSalaryMaster = null;
		

		try {
			roleSalaryMaster = mapper.readValue(roleSalaryMasterInputRequest, RoleSalaryMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		roleSalaryMaster.setUpdatedDate(new java.util.Date());

		roleSalaryMasterService.update(roleSalaryMaster);
		ResponseEntity<String> response = new ResponseEntity<String>("RoleSalaryMaster Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/roleSalaryMasters/{id}" })
	public RoleSalaryMaster delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return roleSalaryMasterService.delete(id);
	}

	@GetMapping(path = { "/api/open/roleSalaryMasters/findAll" })
	public RoleSalaryMasterList findAll() {

		
		java.util.List<RoleSalaryMaster> roleSalaryMasterList = roleSalaryMasterService.findAll();
		
		RoleSalaryMasterList finalRoleSalaryMasterList = new RoleSalaryMasterList(roleSalaryMasterList);
		return finalRoleSalaryMasterList;
	}

	@GetMapping(path = { "/api/roleSalaryMasters/findAll" })
	public RoleSalaryMasterList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<RoleSalaryMaster> roleSalaryMasterList = null;
		
		if (filter.length() > 0) {
			roleSalaryMasterList = roleSalaryMasterService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			roleSalaryMasterList = roleSalaryMasterService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}
		for (RoleSalaryMaster rsm : roleSalaryMasterList) {
			System.out.println("value of rsm start date is " + rsm.getStartDate());
		}
		RoleSalaryMasterList finalRoleSalaryMasterList = new RoleSalaryMasterList(roleSalaryMasterList);

		return finalRoleSalaryMasterList;
	}

	@GetMapping(path = { "/api/roleSalaryMasters/recordCount" })
	public RoleSalaryMasterCount findAll( HttpServletResponse response) {
		
		RoleSalaryMasterCount roleSalaryMasterCount = null;
		Integer countValue = roleSalaryMasterService.countRecord();
		roleSalaryMasterCount = new RoleSalaryMasterCount(countValue);

		return roleSalaryMasterCount;
	}
}