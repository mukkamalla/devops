package com.valign.payroll.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.valign.payroll.webservice.model.*;
import com.valign.payroll.webservice.service.*;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping({ "/api/payrollusers" })

public class PayrollUserController {

	@Autowired
	private PayrollUserService userService;

	@PostMapping
	public PayrollUser create(@RequestBody PayrollUser user, @RequestHeader HttpHeaders headers, HttpServletResponse response) {
		System.out.println("calling create user service..." + user.getFirstName() + " " + user.getEmailId() + " "
				+ user.getLastName());
		System.out.println("printing the authorization token 123 " + headers.get("Authorization"));
		List<String> authorizationList = headers.get("Authorization");
		String authtoken = null;
		for (String auth : authorizationList) {
			authtoken = auth.substring(7);
		}
		return userService.create(user, authtoken);
	}

	@GetMapping(path = { "/{id}" })
	public PayrollUser findById(@PathVariable("id") int id) {
		return userService.findById(id);
	}

	@PutMapping(path = { "/{id}" })
	public PayrollUser update(@PathVariable("id") int id, @RequestBody PayrollUser user) {
		System.out.println("calling update user service..." + user.getFirstName() + " " + user.getEmailId() + " "
				+ user.getLastName());
		user.setId(id);
		return userService.update(user);
	}

	@DeleteMapping(path = { "/{id}" })
	public PayrollUser delete(@PathVariable("id") int id) {
		System.out.println("calling the delete REST service with id "+ id);
		return userService.delete(id);
	}

	@GetMapping
	public PayrollUserList findAll(@RequestParam("sortOrder") String sortOrder,
			@RequestParam("sortOnColumn") String sortOnColumn, @RequestParam("pageNumber") String pageNumber,
			@RequestParam("pageSize") String pageSize, @RequestParam("filter") String filter, HttpServletResponse response) {

		System.out.println("inside finaAll method request parameters are sortOrder " + sortOrder + " , pageNumber "
				+ pageNumber + " , pageSize " + pageSize + " , filter " + filter + " sortOnColumn " + sortOnColumn);
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		java.util.List<PayrollUser> userList = null;
		if (filter.length() > 0) {
			System.out.println("filter " + filter);
			
			userList = userService.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter).getContent();
			System.out.println("return size is " + userList.size());
			for (int i = 0; i < userList.size(); i++) {
				if (i == 0) {
				//	userList.get(i).setTotalCount(userService
				//			.findAllFilter(pageSize, pageNumber, sortOnColumn, sortOrder, filter).getTotalElements());
				}
			}

		} else {

			System.out.println(" No filter ");

			userList = userService.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder).getContent();

			for (int i = 0; i < userList.size(); i++) {
				if (i == 0) {
				//	userList.get(i).setTotalCount(userService
				//			.findAllSortOnColumn(pageSize, pageNumber, sortOnColumn, sortOrder).getTotalElements());
				}
			}

		}
		PayrollUserList finalUserList = new PayrollUserList(userList);
	/*
		response.setHeader("Access-Control-Allow-Origin", "*");
         response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
          response.setHeader("Access-Control-Max-Age", "3600");
          response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
*/
		return finalUserList;
	}
}
