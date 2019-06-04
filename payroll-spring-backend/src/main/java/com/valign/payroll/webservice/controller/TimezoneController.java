package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Timezone;
import com.valign.payroll.webservice.model.TimezoneCount;
import com.valign.payroll.webservice.model.TimezoneList;
import com.valign.payroll.webservice.service.TimezoneService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class TimezoneController {

	@Autowired
	private TimezoneService timezoneService;

	@PostMapping(path = { "/api/timezones" })
	public ResponseEntity<String> create(@RequestBody String timezoneInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Timezone timezone = null;
		try {
			timezone = mapper.readValue(timezoneInputRequest, Timezone.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		timezoneService.create(timezone);
		ResponseEntity<String> response = new ResponseEntity<String>("Timezone Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/timezones/{id}" })
	public ResponseEntity<Timezone> findById(@PathVariable("id") int id) {

		Timezone timezone = timezoneService.findById(id);

		if (timezone == null) {

			return new ResponseEntity<Timezone>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Timezone>(timezone, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/timezones/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String timezoneInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Timezone timezone = null;
		Timezone timezone1 = timezoneService.findById(id);

		try {
			timezone = mapper.readValue(timezoneInputRequest, Timezone.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		timezone1.setGmtOffset(timezone.getGmtOffset());
		timezone1.setRecordStatus(timezone.getRecordStatus());
		timezone1.setTimeZoneId(timezone.getTimeZoneId());
		
		timezone1.setLocaleId(timezone.getLocaleId());

		timezone1.setUpdatedDate(new java.util.Date());

		timezoneService.update(timezone1);
		ResponseEntity<String> response = new ResponseEntity<String>("Timezone Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/timezones/{id}" })
	public Timezone delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return timezoneService.delete(id);
	}

	@GetMapping(path = { "/api/open/timezones/findAll/{localeId}" })
	public TimezoneList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Timezone> timezoneList = timezoneService.findAllByLocaleId(localeId);
		java.util.List<Timezone> timezoneList1 = new java.util.ArrayList<Timezone>();

		for (Timezone timezone : timezoneList) {
			if (timezone.getLocaleId() == localeId) {
				timezoneList1.add(timezone);
			}
		}
		TimezoneList finalTimezoneList = new TimezoneList(timezoneList1);
		return finalTimezoneList;
	}

	@GetMapping(path = { "/api/timezones/findAll/{localeId}" })
	public TimezoneList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Timezone> timezoneList = null;
		java.util.List<Timezone> timezoneList1 = new java.util.ArrayList<Timezone>();
		if (filter.length() > 0) {
			timezoneList = timezoneService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			timezoneList = timezoneService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Timezone timezone : timezoneList) {
			if (timezone.getLocaleId() == localeId) {
				timezoneList1.add(timezone);
			}
		}
		TimezoneList finalTimezoneList = new TimezoneList(timezoneList1);

		return finalTimezoneList;
	}

	@GetMapping(path = { "/api/timezones/recordCount/{localeId}" })
	public TimezoneCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		TimezoneCount timezoneCount = null;
		Integer countValue = timezoneService.countRecordByLocaleId(localeId);
		timezoneCount = new TimezoneCount(countValue);

		return timezoneCount;
	}
}