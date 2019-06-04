package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Salutation;
import com.valign.payroll.webservice.model.SalutationCount;
import com.valign.payroll.webservice.model.SalutationList;
import com.valign.payroll.webservice.service.SalutationService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class SalutationController {

	@Autowired
	private SalutationService salutationService;

	@PostMapping(path = { "/api/salutations" })
	public ResponseEntity<String> create(@RequestBody String salutationsInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Salutation salutation = null;
		try {
			salutation = mapper.readValue(salutationsInputRequest, Salutation.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		salutationService.create(salutation);
		ResponseEntity<String> response = new ResponseEntity<String>("Salutation Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/salutations/{id}" })
	public ResponseEntity<Salutation> findById(@PathVariable("id") int id) {

		Salutation salutation = salutationService.findById(id);

		if (salutation == null) {

			return new ResponseEntity<Salutation>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Salutation>(salutation, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/salutations/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String salutationsInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Salutation salutation = null;
		Salutation salutation1 = salutationService.findById(id);

		try {
			salutation = mapper.readValue(salutationsInputRequest, Salutation.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		salutation1.setSalutation(salutation.getSalutation());
		salutation1.setRecordStatus(salutation.getRecordStatus());
		salutation1.setLocaleId(salutation.getLocaleId());

		salutation1.setUpdatedDate(new java.util.Date());

		salutationService.update(salutation1);
		ResponseEntity<String> response = new ResponseEntity<String>("Salutation Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/salutations/{id}" })
	public Salutation delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return salutationService.delete(id);
	}

	@GetMapping(path = { "/api/open/salutations/findAll/{localeId}" })
	public SalutationList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Salutation> salutationList = salutationService.findAllByLocaleId(localeId);
		java.util.List<Salutation> salutationList1 = new java.util.ArrayList<Salutation>();

		for (Salutation salutation : salutationList) {
			if (salutation.getLocaleId() == localeId) {
				salutationList1.add(salutation);
			}
		}
		SalutationList finalSalutationList = new SalutationList(salutationList1);
		return finalSalutationList;
	}

	@GetMapping(path = { "/api/salutations/findAll/{localeId}" })
	public SalutationList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Salutation> salutationList = null;
		java.util.List<Salutation> salutationList1 = new java.util.ArrayList<Salutation>();
		if (filter.length() > 0) {
			salutationList = salutationService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			salutationList = salutationService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Salutation salutation : salutationList) {
			if (salutation.getLocaleId() == localeId) {
				salutationList1.add(salutation);
			}
		}
		SalutationList finalSalutationList = new SalutationList(salutationList1);

		return finalSalutationList;
	}

	@GetMapping(path = { "/api/salutations/recordCount/{localeId}" })
	public SalutationCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		SalutationCount countryCount = null;
		Integer countValue = salutationService.countRecordByLocaleId(localeId);
		countryCount = new SalutationCount(countValue);

		return countryCount;
	}
}