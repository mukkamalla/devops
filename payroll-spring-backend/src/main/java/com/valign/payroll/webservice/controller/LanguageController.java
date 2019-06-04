package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Language;
import com.valign.payroll.webservice.model.LanguageList;
import com.valign.payroll.webservice.service.LanguageService;
import com.valign.payroll.webservice.model.LanguageCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@PostMapping(path = { "/api/languages" })
	public ResponseEntity<String> create(@RequestBody String countryInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Language language = null;
		try {
			language = mapper.readValue(countryInputRequest, Language.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		languageService.create(language);
		ResponseEntity<String> response = new ResponseEntity<String>("Language Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/languages/{id}" })
	public ResponseEntity<Language> findById(@PathVariable("id") int id) {

		Language language = languageService.findById(id);

		if (language == null) {

			return new ResponseEntity<Language>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Language>(language, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/languages/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String countryInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Language language = null;
		Language country1 = languageService.findById(id);

		try {
			language = mapper.readValue(countryInputRequest, Language.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		country1.setLanguageCode(language.getLanguageCode());
		country1.setName(language.getName());
		country1.setNativeName(language.getNativeName());
		country1.setRecordStatus(language.getRecordStatus());
		

		country1.setUpdatedDate(new java.util.Date());

		languageService.update(country1);
		ResponseEntity<String> response = new ResponseEntity<String>("Language Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/languages/{id}" })
	public Language delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return languageService.delete(id);
	}

	@GetMapping(path = { "/api/open/languages/findAll" })
	public LanguageList findAll() {

		
		java.util.List<Language> languageList = languageService.findAll();
		
		LanguageList finalLanguageList = new LanguageList(languageList);
		return finalLanguageList;
	}

	@GetMapping(path = { "/api/languages/findAll" })
	public LanguageList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Language> languageList = null;
		java.util.List<Language> languageList1 = new java.util.ArrayList<Language>();
		if (filter.length() > 0) {
			languageList = languageService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			languageList = languageService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		LanguageList finalLanguageList = new LanguageList(languageList);

		return finalLanguageList;
	}

	@GetMapping(path = { "/api/languages/recordCount" })
	public LanguageCount findAll( HttpServletResponse response) {
		
		LanguageCount languageCount = null;
		Integer countValue = languageService.countRecord();
		languageCount = new LanguageCount(countValue);

		return languageCount;
	}
}