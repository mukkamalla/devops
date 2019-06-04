package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.DayValue;
import com.valign.payroll.webservice.model.DayValueCount;
import com.valign.payroll.webservice.model.DayValueList;
import com.valign.payroll.webservice.service.DayValueService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class DayValueController {

	@Autowired
	private DayValueService dayValueService;

	@PostMapping(path = { "/api/dayValues" })
	public ResponseEntity<String> create(@RequestBody String dayValueInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		DayValue dayValue = null;
		try {
			dayValue = mapper.readValue(dayValueInputRequest, DayValue.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		dayValueService.create(dayValue);
		ResponseEntity<String> response = new ResponseEntity<String>("DayValue Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/dayValues/{id}" })
	public ResponseEntity<DayValue> findById(@PathVariable("id") int id) {

		DayValue dayValue = dayValueService.findById(id);

		if (dayValue == null) {

			return new ResponseEntity<DayValue>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<DayValue>(dayValue, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/dayValues/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String dayValueInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		DayValue dayValue = null;
		DayValue dayValue1 = dayValueService.findById(id);

		try {
			dayValue = mapper.readValue(dayValueInputRequest, DayValue.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		dayValue1.setDayValueName(dayValue.getDayValueName());
		dayValue1.setDayValueValue(dayValue.getDayValueValue());
		dayValue1.setCompanyId(dayValue.getCompanyId());
		dayValue1.setRecordStatus(dayValue.getRecordStatus());
		dayValue1.setLocaleId(dayValue.getLocaleId());
		dayValue1.setUpdatedDate(new java.util.Date());

		dayValueService.update(dayValue1);
		
		ResponseEntity<String> response = new ResponseEntity<String>("DayValue Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/dayValues/{id}" })
	public DayValue delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return dayValueService.delete(id);
	}

	@GetMapping(path = { "/api/open/dayValues/findAll/{localeId}" })
	public DayValueList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<DayValue> dayValueList = dayValueService.findAllByLocaleId(localeId);
		java.util.List<DayValue> dayValueList1 = new java.util.ArrayList<DayValue>();

		for (DayValue dayValue : dayValueList) {
			if (dayValue.getLocaleId() == localeId) {
				dayValueList1.add(dayValue);
			}
		}
		DayValueList finalCountryList = new DayValueList(dayValueList1);
		return finalCountryList;
	}

	@GetMapping(path = { "/api/dayValues/findAll/{localeId}" })
	public DayValueList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<DayValue> dayValueList = null;
		java.util.List<DayValue> dayValueList1 = new java.util.ArrayList<DayValue>();
		if (filter.length() > 0) {
			dayValueList = dayValueService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			dayValueList = dayValueService.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		for (DayValue dayValue : dayValueList) {
			if (dayValue.getLocaleId() == localeId) {
				dayValueList1.add(dayValue);
			}
		}
		DayValueList finalCountryList = new DayValueList(dayValueList1);

		return finalCountryList;
	}

	@GetMapping(path = { "/api/dayValues/recordCount/{localeId}" })
	public DayValueCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		DayValueCount dayValueCount = null;
		Integer countValue = dayValueService.countRecordByLocaleId(localeId);
		dayValueCount = new DayValueCount(countValue);

		return dayValueCount;
	}
}