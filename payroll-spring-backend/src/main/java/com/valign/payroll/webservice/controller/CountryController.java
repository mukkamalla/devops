package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Country;
import com.valign.payroll.webservice.model.CountryCount;
import com.valign.payroll.webservice.model.CountryList;
import com.valign.payroll.webservice.service.CountryService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class CountryController {

	@Autowired
	private CountryService countryService;

	@PostMapping(path = { "/api/countries" })
	public ResponseEntity<String> create(@RequestBody String countryInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Country country = null;
		try {
			country = mapper.readValue(countryInputRequest, Country.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		countryService.create(country);
		ResponseEntity<String> response = new ResponseEntity<String>("Country Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/countries/{id}" })
	public ResponseEntity<Country> findById(@PathVariable("id") int id) {

		Country country = countryService.findById(id);

		if (country == null) {

			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/countries/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String countryInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Country country = null;
		Country country1 = countryService.findById(id);

		try {
			country = mapper.readValue(countryInputRequest, Country.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		country1.setCode(country.getCode());
		country1.setName(country.getName());
		country1.setFullName(country.getFullName());
		country1.setContinentId(country.getContinentId());
		country1.setIso3(country.getIso3());
		country1.setLocaleId(country.getLocaleId());

		country1.setUpdatedDate(new java.util.Date());

		countryService.update(country1);
		ResponseEntity<String> response = new ResponseEntity<String>("Country Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/countries/{id}" })
	public Country delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return countryService.delete(id);
	}

	@GetMapping(path = { "/api/open/countries/findAll/{localeId}" })
	public CountryList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Country> countryList = countryService.findAllByLocaleId(localeId);
		java.util.List<Country> countryList1 = new java.util.ArrayList<Country>();

		for (Country country : countryList) {
			if (country.getLocaleId() == localeId) {
				countryList1.add(country);
			}
		}
		CountryList finalCountryList = new CountryList(countryList1);
		return finalCountryList;
	}

	@GetMapping(path = { "/api/countries/findAll/{localeId}" })
	public CountryList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Country> countryList = null;
		java.util.List<Country> countryList1 = new java.util.ArrayList<Country>();
		if (filter.length() > 0) {
			countryList = countryService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			countryList = countryService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Country country : countryList) {
			if (country.getLocaleId() == localeId) {
				countryList1.add(country);
			}
		}
		CountryList finalCountryList = new CountryList(countryList1);

		return finalCountryList;
	}

	@GetMapping(path = { "/api/countries/recordCount/{localeId}" })
	public CountryCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		CountryCount countryCount = null;
		Integer countValue = countryService.countRecordByLocaleId(localeId);
		countryCount = new CountryCount(countValue);

		return countryCount;
	}
}