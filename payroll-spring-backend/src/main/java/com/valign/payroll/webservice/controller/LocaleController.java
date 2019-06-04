package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Locale;
import com.valign.payroll.webservice.model.LocaleList;
import com.valign.payroll.webservice.service.LocaleService;
import com.valign.payroll.webservice.model.LocaleCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class LocaleController {

	@Autowired
	private LocaleService localeService;

	@PostMapping(path = { "/api/locales" })
	public ResponseEntity<String> create(@RequestBody String localeInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Locale locale = null;
		try {
			locale = mapper.readValue(localeInputRequest, Locale.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		localeService.create(locale);
		ResponseEntity<String> response = new ResponseEntity<String>("Locale Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/locales/{id}" })
	public ResponseEntity<Locale> findById(@PathVariable("id") int id) {

		Locale locale = localeService.findById(id);

		if (locale == null) {

			return new ResponseEntity<Locale>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Locale>(locale, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/locales/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String localeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Locale locale = null;
		Locale locale1 = localeService.findById(id);

		try {
			locale = mapper.readValue(localeInputRequest, Locale.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		locale1.setLocaleName(locale.getLocaleName());
		locale1.setLanguageId(locale.getLanguageId());
		locale1.setCountryId(locale.getCountryId());
		locale1.setRecordStatus(locale.getRecordStatus());
		

		locale1.setUpdatedDate(new java.util.Date());

		localeService.update(locale1);
		ResponseEntity<String> response = new ResponseEntity<String>("Locale Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/locales/{id}" })
	public Locale delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return localeService.delete(id);
	}

	@GetMapping(path = { "/api/open/locales/findAll" })
	public LocaleList findAll() {

		
		java.util.List<Locale> languageList = localeService.findAll();
		
		LocaleList finalLocaleList = new LocaleList(languageList);
		return finalLocaleList;
	}

	@GetMapping(path = { "/api/locales/findAll" })
	public LocaleList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Locale> localeList = null;
		java.util.List<Locale> localeList1 = new java.util.ArrayList<Locale>();
		if (filter.length() > 0) {
			localeList = localeService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			localeList = localeService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		LocaleList finalLocaleList = new LocaleList(localeList);

		return finalLocaleList;
	}

	@GetMapping(path = { "/api/locales/recordCount" })
	public LocaleCount findAll( HttpServletResponse response) {
		
		LocaleCount localeCount = null;
		Integer countValue = localeService.countRecord();
		localeCount = new LocaleCount(countValue);

		return localeCount;
	}
}