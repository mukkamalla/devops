package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;
import com.valign.payroll.webservice.model.Continent;
import com.valign.payroll.webservice.model.ContinentCount;
import com.valign.payroll.webservice.model.ContinentList;
import com.valign.payroll.webservice.service.ContinentService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@RestController

public class ContinentController {

	@Autowired
	private ContinentService continentService;

	@PostMapping(path = { "/api/continents" })
	public ResponseEntity<String> create(@RequestBody String continentInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Continent continent = null;
		try {
			continent = mapper.readValue(continentInputRequest, Continent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		continentService.create(continent);
		ResponseEntity<String> response = new ResponseEntity<String>("Continent Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/continents/{id}" })
	public ResponseEntity<Continent> findById(@PathVariable("id") int id) {

		Continent continent = continentService.findById(id);

		if (continent == null) {

			return new ResponseEntity<Continent>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Continent>(continent, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/continents/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String continentInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Continent continent = null;
		Continent continent1 = continentService.findById(id);

		try {
			continent = mapper.readValue(continentInputRequest, Continent.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		continent1.setContinentCode(continent.getContinentCode());
		continent1.setContinentName(continent.getContinentName());

		continent1.setUpdatedDate(new java.util.Date());

		continentService.update(continent1);
		ResponseEntity<String> response = new ResponseEntity<String>("Continent Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/continents/{id}" })
	public Continent delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return continentService.delete(id);
	}

	@GetMapping(path = { "/api/open/continents/findAll/{localeId}" })
	public ContinentList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Continent> continentList = continentService.findAllByLocaleId(localeId);
		java.util.List<Continent> continentList1 = new java.util.ArrayList<Continent>();

		for (Continent continent : continentList) {
			if (continent.getLocaleId() == localeId) {
				continentList1.add(continent);
			}
		}
		ContinentList finalContinentList = new ContinentList(continentList1);
		return finalContinentList;
	}

	@GetMapping(path = { "/api/continents/findAll/{localeId}" })
	public ContinentList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Continent> continentList = null;
		java.util.List<Continent> continentList1 = new java.util.ArrayList<Continent>();
		if (filter.length() > 0) {
			continentList = continentService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			continentList = continentService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Continent continent : continentList) {
			if (continent.getLocaleId() == localeId) {
				continentList1.add(continent);
			}
		}
		ContinentList finalContinentList = new ContinentList(continentList1);

		return finalContinentList;
	}

	@GetMapping(path = { "/api/continents/recordCount/{localeId}" })
	public ContinentCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		ContinentCount continentCount = null;
		Integer countValue = continentService.countRecordByLocaleId(localeId);
		continentCount = new ContinentCount(countValue);

		return continentCount;
	}
}