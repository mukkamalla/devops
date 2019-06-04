package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.RecordStatus;
import com.valign.payroll.webservice.model.RecordStatusCount;
import com.valign.payroll.webservice.model.RecordStatusList;
import com.valign.payroll.webservice.service.RecordStatusService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class RecordStatusController {

	@Autowired
	private RecordStatusService recordStatusService;

	@PostMapping(path = { "/api/recordStatuses" })
	public ResponseEntity<String> create(@RequestBody String recordStatusInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RecordStatus recordStatus = null;
		try {
			recordStatus = mapper.readValue(recordStatusInputRequest, RecordStatus.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		recordStatusService.create(recordStatus);
		ResponseEntity<String> response = new ResponseEntity<String>("RecordStatus Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/recordStatuses/{id}" })
	public ResponseEntity<RecordStatus> findById(@PathVariable("id") int id) {

		RecordStatus recordStatus = recordStatusService.findById(id);

		if (recordStatus == null) {

			return new ResponseEntity<RecordStatus>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<RecordStatus>(recordStatus, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/recordStatuses/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String recordStatusInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		RecordStatus recordStatus = null;
		RecordStatus recordStatus1 = recordStatusService.findById(id);

		try {
			recordStatus = mapper.readValue(recordStatusInputRequest, RecordStatus.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		recordStatus1.setLocaleId(recordStatus.getLocaleId());
		recordStatus1.setStatus(recordStatus.getStatus());
		recordStatus1.setLocaleId(recordStatus.getLocaleId());

		recordStatus1.setUpdatedDate(new java.util.Date());

		recordStatusService.update(recordStatus1);
		ResponseEntity<String> response = new ResponseEntity<String>("RecordStatus Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/recordStatuses/{id}" })
	public RecordStatus delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return recordStatusService.delete(id);
	}

	@GetMapping(path = { "/api/open/recordStatuses/findAll/{localeId}" })
	public RecordStatusList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<RecordStatus> countryList = recordStatusService.findAllByLocaleId(localeId);
		java.util.List<RecordStatus> countryList1 = new java.util.ArrayList<RecordStatus>();

		for (RecordStatus recordStatus : countryList) {
			if (recordStatus.getLocaleId() == localeId) {
				countryList1.add(recordStatus);
			}
		}
		RecordStatusList finalCountryList = new RecordStatusList(countryList1);
		return finalCountryList;
	}

	@GetMapping(path = { "/api/recordStatuses/findAll/{localeId}" })
	public RecordStatusList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<RecordStatus> countryList = null;
		java.util.List<RecordStatus> countryList1 = new java.util.ArrayList<RecordStatus>();
		if (filter.length() > 0) {
			countryList = recordStatusService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			countryList = recordStatusService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (RecordStatus recordStatus : countryList) {
			if (recordStatus.getLocaleId() == localeId) {
				countryList1.add(recordStatus);
			}
		}
		RecordStatusList finalCountryList = new RecordStatusList(countryList1);

		return finalCountryList;
	}

	@GetMapping(path = { "/api/recordStatuses/recordCount/{localeId}" })
	public RecordStatusCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		RecordStatusCount recordStatusCount = null;
		Integer countValue = recordStatusService.countRecordByLocaleId(localeId);
		recordStatusCount = new RecordStatusCount(countValue);

		return recordStatusCount;
	}
}