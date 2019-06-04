package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.LeaveType;
import com.valign.payroll.webservice.model.LeaveTypeList;
import com.valign.payroll.webservice.service.LeaveTypeService;
import com.valign.payroll.webservice.model.LeaveTypeCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class LeaveTypeController {

	@Autowired
	private LeaveTypeService leaveTypeService;

	@PostMapping(path = { "/api/leaveTypes" })
	public ResponseEntity<String> create(@RequestBody String leaveTypeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		LeaveType leaveType = null;
		try {
			leaveType = mapper.readValue(leaveTypeInputRequest, LeaveType.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		leaveTypeService.create(leaveType);
		ResponseEntity<String> response = new ResponseEntity<String>("LeaveType Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/leaveTypes/{id}" })
	public ResponseEntity<LeaveType> findById(@PathVariable("id") int id) {

		LeaveType leaveType = leaveTypeService.findById(id);

		if (leaveType == null) {

			return new ResponseEntity<LeaveType>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<LeaveType>(leaveType, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/leaveTypes/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String leaveTypeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		LeaveType leaveType = null;

		try {
			leaveType = mapper.readValue(leaveTypeInputRequest, LeaveType.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		leaveType.setUpdatedDate(new java.util.Date());

		leaveTypeService.update(leaveType);
		ResponseEntity<String> response = new ResponseEntity<String>("LeaveType Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/leaveTypes/{id}" })
	public LeaveType delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return leaveTypeService.delete(id);
	}

	@GetMapping(path = { "/api/open/leaveTypes/findAll" })
	public LeaveTypeList findAll() {

		java.util.List<LeaveType> leaveTypeList = leaveTypeService.findAll();

		LeaveTypeList finalLeaveTypeList = new LeaveTypeList(leaveTypeList);
		return finalLeaveTypeList;
	}

	@GetMapping(path = { "/api/leaveTypes/findAll" })
	public LeaveTypeList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<LeaveType> leaveTypeList = null;

		if (filter.length() > 0) {
			leaveTypeList = leaveTypeService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			leaveTypeList = leaveTypeService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		LeaveTypeList finalLeaveTypeList = new LeaveTypeList(leaveTypeList);

		return finalLeaveTypeList;
	}

	@GetMapping(path = { "/api/leaveTypes/recordCount" })
	public LeaveTypeCount findAll(HttpServletResponse response) {

		LeaveTypeCount leaveTypeCount = null;
		Integer countValue = leaveTypeService.countRecord();
		leaveTypeCount = new LeaveTypeCount(countValue);

		return leaveTypeCount;
	}
}