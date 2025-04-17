package com.PMS.PMSServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.AuthCredentials;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.SessionResponse;
import com.PMS.PMSServer.service.AuthService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthService service;

	@PostMapping("/login")
	public ResponseEntity<SessionResponse> login(@RequestBody AuthCredentials credentials, HttpSession session)
			throws Exception {
		return ResponseEntity.ok(service.login(credentials, session));
	}

	@PostMapping("/signup")
	public ResponseEntity<SessionResponse> signup(@RequestBody PMSUser user, HttpSession session) throws Exception {
		return ResponseEntity.ok(service.register(user, session));
	}

	@GetMapping("/logout")
	public ResponseEntity<SessionResponse> logout(HttpSession session) throws IllegalStateException{
		return ResponseEntity.ok(service.logout(session));
	}

}
