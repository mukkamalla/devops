package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.Role;
import com.valign.payroll.webservice.model.RoleList;
import com.valign.payroll.webservice.service.RoleService;
import com.valign.payroll.webservice.model.RoleCount;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;

import com.valign.payroll.webservice.exception.InvalidJSONFormatException;

import java.io.IOException;

@RestController

public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping(path = { "/api/roles" })
	public ResponseEntity<String> create(@RequestBody String roleInputRequest) throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Role role = null;
		try {
			role = mapper.readValue(roleInputRequest, Role.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		roleService.create(role);
		ResponseEntity<String> response = new ResponseEntity<String>("Role Entity Created", HttpStatus.OK);

		return response;
	}

	@GetMapping(path = { "/api/roles/{id}" })
	public ResponseEntity<Role> findById(@PathVariable("id") int id) {

		Role role = roleService.findById(id);

		if (role == null) {

			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		} else

			return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@PutMapping(path = { "/api/roles/{id}" })
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody String roleInputRequest)
			throws InvalidJSONFormatException {
		ObjectMapper mapper = new ObjectMapper();

		Role role = null;
		

		try {
			role = mapper.readValue(roleInputRequest, Role.class);

		} catch (org.codehaus.jackson.map.JsonMappingException e) {

			throw new InvalidJSONFormatException("invalid input JSON",
					new Throwable("details message - invalid input JSON"));
		}

		catch (IOException e) {
			e.printStackTrace();

		}
		
		role.setUpdatedDate(new java.util.Date());

		roleService.update(role);
		ResponseEntity<String> response = new ResponseEntity<String>("Role Entity Updated", HttpStatus.OK);

		return response;
	}

	@DeleteMapping(path = { "/api/roles/{id}" })
	public Role delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id " + id);
		return roleService.delete(id);
	}

	@GetMapping(path = { "/api/open/roles/findAll" })
	public RoleList findAll() {

		
		java.util.List<Role> roleList = roleService.findAll();
		
		RoleList finalRoleList = new RoleList(roleList);
		return finalRoleList;
	}

	@GetMapping(path = { "/api/roles/findAll" })
	public RoleList findAll(
			@RequestParam("sortOrder") String sortOrder, @RequestParam("sortOnColumn") String sortOnColumn,
			@RequestParam("pageNumber") String pageNumber, @RequestParam("pageSize") String pageSize,
			@RequestParam("filter") String filter, HttpServletResponse response) {
		
		java.util.List<Role> roleList = null;
		
		if (filter.length() > 0) {
			roleList = roleService
					.findAllFilter( pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();

		} else {

			roleList = roleService.findAllSortOnColumn( pageSize, pageNumber, sortOnColumn, sortOrder)
					.getContent();

		}

		RoleList finalRoleList = new RoleList(roleList);

		return finalRoleList;
	}

	@GetMapping(path = { "/api/roles/recordCount" })
	public RoleCount findAll( HttpServletResponse response) {
		
		RoleCount roleCount = null;
		Integer countValue = roleService.countRecord();
		roleCount = new RoleCount(countValue);

		return roleCount;
	}
}