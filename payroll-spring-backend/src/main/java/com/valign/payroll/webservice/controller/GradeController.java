package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Grade;
import com.valign.payroll.webservice.model.GradeList;
import com.valign.payroll.webservice.service.GradeService;
import com.valign.payroll.webservice.model.GradeCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class GradeController {

	@Autowired
	private GradeService gradeService;

	@PostMapping(path = { "/api/grades" })
	public ResponseEntity<String> create(@RequestBody String gradeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Grade grade = null;
		try {
			grade = mapper.readValue(gradeInputRequest, Grade.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		gradeService.create(grade);
		ResponseEntity<String> response = new ResponseEntity<String>("Grade Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/grades/{id}" })
	public ResponseEntity<Grade> findById(@PathVariable("id") int id) {

		Grade grade = gradeService.findById(id);

		if (grade == null) {

			return new ResponseEntity<Grade>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/grades/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String gradeInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Grade grade = null;

		try {
			grade = mapper.readValue(gradeInputRequest, Grade.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		grade.setUpdatedDate(new java.util.Date());

		gradeService.update(grade);
		ResponseEntity<String> response = new ResponseEntity<String>("Grade Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/grades/{id}" })
	public Grade delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return gradeService.delete(id);
	}

	@GetMapping(path = { "/api/open/grades/findAll" })
	public GradeList findAll() {

		java.util.List<Grade> gradeList = gradeService.findAll();

		GradeList finalGradeList = new GradeList(gradeList);
		return finalGradeList;
	}

	@GetMapping(path = { "/api/grades/findAll" })
	public GradeList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter,
			HttpServletResponse response) {

		java.util.List<Grade> gradeList = null;

		if (filter.length() > 0) {
			gradeList = gradeService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter)
					.getContent();

		} else {

			gradeList = gradeService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		GradeList finalGradeList = new GradeList(gradeList);

		return finalGradeList;
	}

	@GetMapping(path = { "/api/grades/recordCount" })
	public GradeCount findAll(HttpServletResponse response) {

		GradeCount gradeCount = null;
		Integer countValue = gradeService.countRecord();
		gradeCount = new GradeCount(countValue);

		return gradeCount;
	}
}