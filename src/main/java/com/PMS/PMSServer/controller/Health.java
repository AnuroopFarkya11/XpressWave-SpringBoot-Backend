package com.PMS.PMSServer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@CrossOrigin("*")
public class Health {

	@GetMapping()
	public ResponseEntity<Map<String,String>> checkStatus()
	{
		Map<String,String> response = new HashMap<>();
		response.put("status", HttpStatus.OK.toString());
		response.put("remarks", "PMS SERVER IS WORKING FINE");
		return  ResponseEntity.ok(response);
	}
}
