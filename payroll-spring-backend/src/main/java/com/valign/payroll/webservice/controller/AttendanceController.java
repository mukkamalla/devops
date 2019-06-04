package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Attendance;
import com.valign.payroll.webservice.model.AttendanceList;
import com.valign.payroll.webservice.service.AttendanceService;
import com.valign.payroll.webservice.model.AttendanceCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@PostMapping(path = { "/api/attendances" })
	public ResponseEntity<String> create(@RequestBody String attendanceInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Attendance attendance = null;
		try {
			attendance = mapper.readValue(attendanceInputRequest, Attendance.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		attendanceService.create(attendance);
		ResponseEntity<String> response = new ResponseEntity<String>("Attendance Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/attendances/{id}" })
	public ResponseEntity<Attendance> findById(@PathVariable("id") int id) {

		Attendance attendance = attendanceService.findById(id);

		if (attendance == null) {

			return new ResponseEntity<Attendance>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/attendances/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String attendanceInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Attendance attendance = null;
		
		try {
			attendance = mapper.readValue(attendanceInputRequest, Attendance.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		attendance.setUpdatedDate(new java.util.Date());

		attendanceService.update(attendance);
		ResponseEntity<String> response = new ResponseEntity<String>("Attendance Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/attendances/{id}" })
	public Attendance delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return attendanceService.delete(id);
	}

	@GetMapping(path = { "/api/open/attendances/findAll" })
	public AttendanceList findAll() {

		
		java.util.List<Attendance> attendanceList = attendanceService.findAll();
		
		AttendanceList finalAttendanceList = new AttendanceList(attendanceList);
		return finalAttendanceList;
	}

	@GetMapping(path = { "/api/attendances/findAll" })
	public AttendanceList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Attendance> attendanceList = null;
		
		if (filter.length() > 0) {
			attendanceList = attendanceService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			attendanceList = attendanceService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		AttendanceList finalAttendanceList = new AttendanceList(attendanceList);

		return finalAttendanceList;
	}

	@GetMapping(path = { "/api/attendances/recordCount" })
	public AttendanceCount findAll( HttpServletResponse response) {
		
		AttendanceCount attendanceCount = null;
		Integer countValue = attendanceService.countRecord();
		attendanceCount = new AttendanceCount(countValue);

		return attendanceCount;
	}
}