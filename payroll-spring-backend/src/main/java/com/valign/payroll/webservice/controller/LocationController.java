package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Location;
import com.valign.payroll.webservice.model.LocationList;
import com.valign.payroll.webservice.service.LocationService;
import com.valign.payroll.webservice.model.LocationCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class LocationController {

	@Autowired
	private LocationService locationService;

	@PostMapping(path = { "/api/locations" })
	public ResponseEntity<String> create(@RequestBody String locationInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Location location = null;
		try {
			location = mapper.readValue(locationInputRequest, Location.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		locationService.create(location);
		ResponseEntity<String> response = new ResponseEntity<String>("Location Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/locations/{id}" })
	public ResponseEntity<Location> findById(@PathVariable("id") int id) {

		Location location = locationService.findById(id);

		if (location == null) {

			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Location>(location, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/locations/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String locationInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Location location = null;
		

		try {
			location = mapper.readValue(locationInputRequest, Location.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		location.setUpdatedDate(new java.util.Date());

		locationService.update(location);
		ResponseEntity<String> response = new ResponseEntity<String>("Location Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/locations/{id}" })
	public Location delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return locationService.delete(id);
	}

	@GetMapping(path = { "/api/open/locations/findAll" })
	public LocationList findAll() {

		
		java.util.List<Location> locationList = locationService.findAll();
		
		LocationList finalLocationList = new LocationList(locationList);
		return finalLocationList;
	}

	@GetMapping(path = { "/api/locations/findAll" })
	public LocationList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Location> locationList = null;
		
		if (filter.length() > 0) {
			locationList = locationService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			locationList = locationService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		LocationList finalLocationList = new LocationList(locationList);

		return finalLocationList;
	}

	@GetMapping(path = { "/api/locations/recordCount" })
	public LocationCount findAll( HttpServletResponse response) {
		
		LocationCount localeCount = null;
		Integer countValue = locationService.countRecord();
		localeCount = new LocationCount(countValue);

		return localeCount;
	}
}