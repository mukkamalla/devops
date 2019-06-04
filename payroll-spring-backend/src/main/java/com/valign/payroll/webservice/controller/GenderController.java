package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Gender;
import com.valign.payroll.webservice.model.GenderCount;
import com.valign.payroll.webservice.model.GenderList;
import com.valign.payroll.webservice.service.GenderService;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class GenderController {

	@Autowired
	private GenderService genderService;

	@PostMapping(path = { "/api/genders" })
	public ResponseEntity<String> create(@RequestBody String genderInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Gender gender = null;
		try {
			gender = mapper.readValue(genderInputRequest, Gender.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		genderService.create(gender);
		ResponseEntity<String> response = new ResponseEntity<String>("Gender Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/genders/{id}" })
	public ResponseEntity<Gender> findById(@PathVariable("id") int id) {

		Gender gender = genderService.findById(id);

		if (gender == null) {

			return new ResponseEntity<Gender>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Gender>(gender, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/genders/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String genderInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Gender gender = null;
		Gender gender1 = genderService.findById(id);

		try {
			gender = mapper.readValue(genderInputRequest, Gender.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		gender1.setGender(gender.getGender());
		gender1.setRecordStatus(gender.getRecordStatus());
		gender1.setLocaleId(gender.getLocaleId());

		gender1.setUpdatedDate(new java.util.Date());

		genderService.update(gender1);
		ResponseEntity<String> response = new ResponseEntity<String>("Gender Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/genders/{id}" })
	public Gender delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return genderService.delete(id);
	}

	@GetMapping(path = { "/api/open/genders/findAll/{localeId}" })
	public GenderList findAll(@PathVariable("localeId") String localeIdStr) {

		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Gender> genderList = genderService.findAllByLocaleId(localeId);
		java.util.List<Gender> genderList1 = new java.util.ArrayList<Gender>();

		for (Gender gender : genderList) {
			if (gender.getLocaleId() == localeId) {
				genderList1.add(gender);
			}
		}
		GenderList finalGenderList = new GenderList(genderList1);
		return finalGenderList;
	}

	@GetMapping(path = { "/api/genders/findAll/{localeId}" })
	public GenderList findAll(@PathVariable("localeId") String localeIdStr,
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		java.util.List<Gender> genderList = null;
		java.util.List<Gender> genderList1 = new java.util.ArrayList<Gender>();
		if (filter.length() > 0) {
			genderList = genderService
					.findAllFilter(localeId, pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			genderList = genderService
					.findAllSortOnColumn(localeId, pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

		}

		for (Gender gender : genderList) {
			if (gender.getLocaleId() == localeId) {
				genderList1.add(gender);
			}
		}
		GenderList finalGenderList = new GenderList(genderList1);

		return finalGenderList;
	}

	@GetMapping(path = { "/api/genders/recordCount/{localeId}" })
	public GenderCount findAll(@PathVariable("localeId") String localeIdStr, HttpServletResponse response) {
		int localeId = new Integer(localeIdStr).intValue();
		GenderCount genderCount = null;
		Integer countValue = genderService.countRecordByLocaleId(localeId);
		genderCount = new GenderCount(countValue);

		return genderCount;
	}
}