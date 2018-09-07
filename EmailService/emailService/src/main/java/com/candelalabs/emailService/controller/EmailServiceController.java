package com.candelalabs.emailService.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candelalabs.emailService.service.EmailService;



@RestController
public class EmailServiceController {

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceController.class);

	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Email service is running successfully");
		return "Email service is running successfully";
	}
	
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> sendMail(Locale locale, Model model) {
		Map<String, Object> response = new HashMap<String, Object>();
		EmailService eService=new EmailService();
		if (eService.sendMail()){
			response.put("status", "ok");
			response.put("msg", "Email sent successfully");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		response.put("status", "error");
		response.put("msg", "Email Not sent");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
	}

	
}
