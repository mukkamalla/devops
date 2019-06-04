package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Shift;
import com.valign.payroll.webservice.model.ShiftList;
import com.valign.payroll.webservice.service.ShiftService;
import com.valign.payroll.webservice.model.ShiftCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class ShiftController {

	@Autowired
	private ShiftService shiftService;

	@PostMapping(path = { "/api/shifts" })
	public ResponseEntity<String> create(@RequestBody String shiftInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Shift shift = null;
		try {
			shift = mapper.readValue(shiftInputRequest, Shift.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		shiftService.create(shift);
		ResponseEntity<String> response = new ResponseEntity<String>("Shift Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/shifts/{id}" })
	public ResponseEntity<Shift> findById(@PathVariable("id") int id) {

		Shift shift = shiftService.findById(id);

		if (shift == null) {

			return new ResponseEntity<Shift>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Shift>(shift, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/shifts/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String shiftInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Shift shift = null;
		

		try {
			shift = mapper.readValue(shiftInputRequest, Shift.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		shift.setUpdatedDate(new java.util.Date());

		shiftService.update(shift);
		ResponseEntity<String> response = new ResponseEntity<String>("Shift Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/shifts/{id}" })
	public Shift delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return shiftService.delete(id);
	}

	@GetMapping(path = { "/api/open/shifts/findAll" })
	public ShiftList findAll() {

		
		java.util.List<Shift> shiftList = shiftService.findAll();
		
		ShiftList finalShiftList = new ShiftList(shiftList);
		return finalShiftList;
	}

	@GetMapping(path = { "/api/shifts/findAll" })
	public ShiftList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Shift> shiftList = null;
		
		if (filter.length() > 0) {
			shiftList = shiftService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			shiftList = shiftService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		ShiftList finalShiftList = new ShiftList(shiftList);

		return finalShiftList;
	}

	@GetMapping(path = { "/api/shifts/recordCount" })
	public ShiftCount findAll( HttpServletResponse response) {
		
		ShiftCount localeCount = null;
		Integer countValue = shiftService.countRecord();
		localeCount = new ShiftCount(countValue);

		return localeCount;
	}
}