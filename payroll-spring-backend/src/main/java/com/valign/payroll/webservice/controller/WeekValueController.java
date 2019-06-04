package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.WeekValue;
import com.valign.payroll.webservice.model.WeekValueCount;
import com.valign.payroll.webservice.model.WeekValueList;
import com.valign.payroll.webservice.service.WeekValueService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class WeekValueController {

	@Autowired
	private WeekValueService weekValueService;

	@PostMapping(path = { "/api/weekValues" })
	public ResponseEntity<String> create(@RequestBody String weekValueInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		WeekValue weekValue = null;
		try {
			weekValue = mapper.readValue(weekValueInputRequest, WeekValue.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		weekValueService.create(weekValue);
		ResponseEntity<String> response = new ResponseEntity<String>("WeekValue Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/weekValues/{id}" })
	public ResponseEntity<WeekValue> findById(@PathVariable("id") int id) {

		WeekValue weekValue = weekValueService.findById(id);

		if (weekValue == null) {

			return new ResponseEntity<WeekValue>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<WeekValue>(weekValue, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/weekValues/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String weekValueInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		WeekValue weekValue = null;
		WeekValue weekValue1 = weekValueService.findById(id);

		try {
			weekValue = mapper.readValue(weekValueInputRequest, WeekValue.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		weekValue1.setWeekValueName(weekValue.getWeekValueName());
		weekValue1.setWeekValueValue(weekValue.getWeekValueValue());
		weekValue1.setRecordStatus(weekValue.getRecordStatus());
		weekValue1.setLocaleId(weekValue.getLocaleId());

		weekValue1.setUpdatedDate(new java.util.Date());

		weekValueService.update(weekValue1);
		ResponseEntity<String> response = new ResponseEntity<String>("WeekValue Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/weekValues/{id}" })
	public WeekValue delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return weekValueService.delete(id);
	}

	@GetMapping(path = { "/api/open/weekValues/findAll/{localeId}" })
	public WeekValueList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<WeekValue> weekValueList = weekValueService.findAllByLocaleId(localeId);
		java.util.List<WeekValue> weekValueList1 = new java.util.ArrayList<WeekValue>();

		for (WeekValue weekValue : weekValueList) {
			if (weekValue.getLocaleId() == localeId) {
				weekValueList1.add(weekValue);
			}
		}
		WeekValueList finalCountryList = new WeekValueList(weekValueList1);
		return finalCountryList;
	}

	@GetMapping(path = { "/api/weekValues/findAll/{localeId}" })
	public WeekValueList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<WeekValue> weekValueList = null;
		java.util.List<WeekValue> weekValueList1 = new java.util.ArrayList<WeekValue>();
		if (filter.length() > 0) {
			weekValueList = weekValueService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			weekValueList = weekValueService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (WeekValue weekValue : weekValueList) {
			if (weekValue.getLocaleId() == localeId) {
				weekValueList1.add(weekValue);
			}
		}
		WeekValueList finalCountryList = new WeekValueList(weekValueList1);

		return finalCountryList;
	}

	@GetMapping(path = { "/api/weekValues/recordCount/{localeId}" })
	public WeekValueCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		WeekValueCount countryCount = null;
		Integer countValue = weekValueService.countRecordByLocaleId(localeId);
		countryCount = new WeekValueCount(countValue);

		return countryCount;
	}
}