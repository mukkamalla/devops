package com.valign.payroll.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayrollApplication {
	
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/payroll");
		SpringApplication.run(PayrollApplication.class, args);
	}
}
