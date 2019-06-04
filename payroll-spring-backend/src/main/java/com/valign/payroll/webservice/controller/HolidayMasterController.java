package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.HolidayMaster;
import com.valign.payroll.webservice.model.HolidayMasterList;
import com.valign.payroll.webservice.service.HolidayMasterService;
import com.valign.payroll.webservice.model.HolidayMasterCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class HolidayMasterController {

	@Autowired
	private HolidayMasterService holidayMasterService;

	@PostMapping(path = { "/api/holidayMasters" })
	public ResponseEntity<String> create(@RequestBody String holidayMasterInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		HolidayMaster holidayMaster = null;
		try {
			holidayMaster = mapper.readValue(holidayMasterInputRequest, HolidayMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		holidayMasterService.create(holidayMaster);
		ResponseEntity<String> response = new ResponseEntity<String>("HolidayMaster Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/holidayMasters/{id}" })
	public ResponseEntity<HolidayMaster> findById(@PathVariable("id") int id) {

		HolidayMaster holidayMaster = holidayMasterService.findById(id);

		if (holidayMaster == null) {

			return new ResponseEntity<HolidayMaster>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<HolidayMaster>(holidayMaster, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/holidayMasters/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String holidayMasterInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		HolidayMaster holidayMaster = null;
		

		try {
			holidayMaster = mapper.readValue(holidayMasterInputRequest, HolidayMaster.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		holidayMaster.setUpdatedDate(new java.util.Date());

		holidayMasterService.update(holidayMaster);
		ResponseEntity<String> response = new ResponseEntity<String>("HolidayMaster Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/holidayMasters/{id}" })
	public HolidayMaster delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return holidayMasterService.delete(id);
	}

	@GetMapping(path = { "/api/open/holidayMasters/findAll" })
	public HolidayMasterList findAll() {

		
		java.util.List<HolidayMaster> holidayMasterList = holidayMasterService.findAll();
		
		HolidayMasterList finalHolidayMasterList = new HolidayMasterList(holidayMasterList);
		return finalHolidayMasterList;
	}

	@GetMapping(path = { "/api/holidayMasters/findAll" })
	public HolidayMasterList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<HolidayMaster> holidayMasterList = null;
		
		if (filter.length() > 0) {
			holidayMasterList = holidayMasterService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			holidayMasterList = holidayMasterService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		HolidayMasterList finalHolidayMasterList = new HolidayMasterList(holidayMasterList);

		return finalHolidayMasterList;
	}

	@GetMapping(path = { "/api/holidayMasters/recordCount" })
	public HolidayMasterCount findAll( HttpServletResponse response) {
		
		HolidayMasterCount localeCount = null;
		Integer countValue = holidayMasterService.countRecord();
		localeCount = new HolidayMasterCount(countValue);

		return localeCount;
	}
}