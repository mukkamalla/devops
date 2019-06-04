package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.SupportedLocales;
import com.valign.payroll.webservice.model.SupportedLocalesList;
import com.valign.payroll.webservice.service.SupportedLocalesService;
import com.valign.payroll.webservice.model.SupportedLocalesCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class SupportedLocalesController {

	@Autowired
	private SupportedLocalesService supportedLocalesService;

	@PostMapping(path = { "/api/supportedLocales" })
	public ResponseEntity<String> create(@RequestBody String supportedLocalesInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		SupportedLocales supportedLocales = null;
		try {
			supportedLocales = mapper.readValue(supportedLocalesInputRequest, SupportedLocales.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		supportedLocalesService.create(supportedLocales);
		ResponseEntity<String> response = new ResponseEntity<String>("SupportedLocales Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/supportedLocales/{id}" })
	public ResponseEntity<SupportedLocales> findById(@PathVariable("id") int id) {

		SupportedLocales supportedLocales = supportedLocalesService.findById(id);

		if (supportedLocales == null) {

			return new ResponseEntity<SupportedLocales>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<SupportedLocales>(supportedLocales, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/supportedLocales/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String supportedLocalesInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		SupportedLocales supportedLocales = null;
		

		try {
			supportedLocales = mapper.readValue(supportedLocalesInputRequest, SupportedLocales.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		supportedLocales.setUpdatedDate(new java.util.Date());

		supportedLocalesService.update(supportedLocales);
		ResponseEntity<String> response = new ResponseEntity<String>("SupportedLocales Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/supportedLocales/{id}" })
	public SupportedLocales delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return supportedLocalesService.delete(id);
	}

	@GetMapping(path = { "/api/open/supportedLocales/findAll" })
	public SupportedLocalesList findAll() {

		
		java.util.List<SupportedLocales> supportedLocalesList = supportedLocalesService.findAll();
		
		SupportedLocalesList finalSupportedLocalesList = new SupportedLocalesList(supportedLocalesList);
		return finalSupportedLocalesList;
	}

	@GetMapping(path = { "/api/supportedLocales/findAll" })
	public SupportedLocalesList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<SupportedLocales> supportedLocalesList = null;
		
		if (filter.length() > 0) {
			supportedLocalesList = supportedLocalesService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			supportedLocalesList = supportedLocalesService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		SupportedLocalesList finalSupportedLocalesList = new SupportedLocalesList(supportedLocalesList);

		return finalSupportedLocalesList;
	}

	@GetMapping(path = { "/api/supportedLocales/recordCount" })
	public SupportedLocalesCount findAll( HttpServletResponse response) {
		
		SupportedLocalesCount localeCount = null;
		Integer countValue = supportedLocalesService.countRecord();
		localeCount = new SupportedLocalesCount(countValue);

		return localeCount;
	}
}