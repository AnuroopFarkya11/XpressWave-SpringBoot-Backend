package com.PMS.PMSServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
//@Api(value = "User Endpoints", tags = {"User Controller"})
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/all")
	public ResponseEntity<?> selectAll(HttpSession session) throws Exception {
//		SessionUtil.isLoggedIn(session);
		List<PMSUser> users = service.getAllUsers();
		return ResponseEntity.ok().body(users);
	}
// 
	
	
	
	
	
	
//	@GetMapping
//	public ResponseEntity<?> selectUserByID(@RequestParam int id) throws Exception {
//
//		PMSUser user = service.getUser(id);
//
//		return ResponseEntity.ok().body(user);
//
//	}

	@GetMapping
	public ResponseEntity<?> selectUser(@RequestParam int id,HttpSession session) throws Exception {
		
//		int id = SessionUtil.getUserId(session);
		
		
//		System.out.println(session);
//		SessionUtil.isLoggedIn(session);
		PMSUser user = service.getUser(id);
		return ResponseEntity.ok().body(user);
	}

//	@PostMapping
//	public ResponseEntity<?> create(@RequestBody PMSUser u, HttpSession session) throws Exception {
//
//		PMSUser user = service.addUser(u);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(user);
//
//	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody PMSUser obj, HttpSession session) throws Exception {
		int id = SessionUtil.getUserId(session);
		PMSUser user = service.updateUser(id, obj);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PutMapping("/forgetPassword")
    public ResponseEntity<?> updatePasswordByEmail(@RequestBody PMSUser obj) throws Exception {
        String email=obj.getEmail();
        String password=obj.getPassword();
        PMSUser User=service.updatePasswordBYEmail(email, password);
    
        return ResponseEntity.status(HttpStatus.OK).body(User);
    }

}
