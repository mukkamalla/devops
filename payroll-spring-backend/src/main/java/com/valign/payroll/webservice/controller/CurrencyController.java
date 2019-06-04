package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Currency;
import com.valign.payroll.webservice.model.CurrencyCount;
import com.valign.payroll.webservice.model.CurrencyList;
import com.valign.payroll.webservice.service.CurrencyService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@PostMapping(path = { "/api/currencies" })
	public ResponseEntity<String> create(@RequestBody String currencyInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Currency currency = null;
		try {
			currency = mapper.readValue(currencyInputRequest, Currency.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		currencyService.create(currency);
		ResponseEntity<String> response = new ResponseEntity<String>("Currency Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/currencies/{id}" })
	public ResponseEntity<Currency> findById(@PathVariable("id") int id) {

		Currency currency = currencyService.findById(id);

		if (currency == null) {

			return new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Currency>(currency, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/currencies/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String currencyInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Currency currency = null;
		Currency currency1 = currencyService.findById(id);

		try {
			currency = mapper.readValue(currencyInputRequest, Currency.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		currency1.setCode(currency.getCode());
		currency1.setCountry(currency.getCountry());
		currency1.setCountryId(currency.getCountryId());
		currency1.setLocaleId(currency.getLocaleId());
		currency1.setCurrency(currency.getCurrency());
		currency1.setLocaleId(currency.getLocaleId());
		currency1.setSymbol(currency.getSymbol());

		currency1.setUpdatedDate(new java.util.Date());

		currencyService.update(currency1);
		ResponseEntity<String> response = new ResponseEntity<String>("Currency Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/currencies/{id}" })
	public Currency delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return currencyService.delete(id);
	}

	@GetMapping(path = { "/api/open/currencies/findAll/{localeId}" })
	public CurrencyList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Currency> currencyList = currencyService.findAllByLocaleId(localeId);
		java.util.List<Currency> currencyList1 = new java.util.ArrayList<Currency>();

		for (Currency currency : currencyList) {
			if (currency.getLocaleId() == localeId) {
				currencyList1.add(currency);
			}
		}
		CurrencyList finalContinentList = new CurrencyList(currencyList1);
		return finalContinentList;
	}

	@GetMapping(path = { "/api/currencies/findAll/{localeId}" })
	public CurrencyList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Currency> currencyList = null;
		java.util.List<Currency> currencyList1 = new java.util.ArrayList<Currency>();
		if (filter.length() > 0) {
			currencyList = currencyService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			currencyList = currencyService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Currency currency : currencyList) {
			if (currency.getLocaleId() == localeId) {
				currencyList1.add(currency);
			}
		}
		CurrencyList finalContinentList = new CurrencyList(currencyList1);

		return finalContinentList;
	}

	@GetMapping(path = { "/api/currencies/recordCount/{localeId}" })
	public CurrencyCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		CurrencyCount currencyCount = null;
		Integer countValue = currencyService.countRecordByLocaleId(localeId);
		currencyCount = new CurrencyCount(countValue);

		return currencyCount;
	}
}