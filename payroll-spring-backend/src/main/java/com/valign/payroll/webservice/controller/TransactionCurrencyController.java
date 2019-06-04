package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.TransactionCurrency;
import com.valign.payroll.webservice.model.TransactionCurrencyList;
import com.valign.payroll.webservice.service.TransactionCurrencyService;
import com.valign.payroll.webservice.model.TransactionCurrencyCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class TransactionCurrencyController {

	@Autowired
	private TransactionCurrencyService transactionCurrencyService;

	@PostMapping(path = { "/api/transactionCurrencies" })
	public ResponseEntity<String> create(@RequestBody String transactionCurrencyInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		TransactionCurrency shift = null;
		try {
			shift = mapper.readValue(transactionCurrencyInputRequest, TransactionCurrency.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		transactionCurrencyService.create(shift);
		ResponseEntity<String> response = new ResponseEntity<String>("TransactionCurrency Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/transactionCurrencies/{id}" })
	public ResponseEntity<TransactionCurrency> findById(@PathVariable("id") int id) {

		TransactionCurrency shift = transactionCurrencyService.findById(id);

		if (shift == null) {

			return new ResponseEntity<TransactionCurrency>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<TransactionCurrency>(shift, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/transactionCurrencies/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String transactionCurrencyInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		TransactionCurrency shift = null;
		

		try {
			shift = mapper.readValue(transactionCurrencyInputRequest, TransactionCurrency.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		shift.setUpdatedDate(new java.util.Date());

		transactionCurrencyService.update(shift);
		ResponseEntity<String> response = new ResponseEntity<String>("TransactionCurrency Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/transactionCurrencies/{id}" })
	public TransactionCurrency delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return transactionCurrencyService.delete(id);
	}

	@GetMapping(path = { "/api/open/transactionCurrencies/findAll" })
	public TransactionCurrencyList findAll() {

		
		java.util.List<TransactionCurrency> transactionCurrencyList = transactionCurrencyService.findAll();
		
		TransactionCurrencyList finalTransactionCurrencyList = new TransactionCurrencyList(transactionCurrencyList);
		return finalTransactionCurrencyList;
	}

	@GetMapping(path = { "/api/transactionCurrencies/findAll" })
	public TransactionCurrencyList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<TransactionCurrency> transactionCurrencyList = null;
		
		if (filter.length() > 0) {
			transactionCurrencyList = transactionCurrencyService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			transactionCurrencyList = transactionCurrencyService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		TransactionCurrencyList finalTransactionCurrencyList = new TransactionCurrencyList(transactionCurrencyList);

		return finalTransactionCurrencyList;
	}

	@GetMapping(path = { "/api/transactionCurrencies/recordCount" })
	public TransactionCurrencyCount findAll( HttpServletResponse response) {
		
		TransactionCurrencyCount localeCount = null;
		Integer countValue = transactionCurrencyService.countRecord();
		localeCount = new TransactionCurrencyCount(countValue);

		return localeCount;
	}
}