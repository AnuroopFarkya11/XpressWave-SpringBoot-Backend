package com.PMS.PMSServer.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PMS.PMSServer.model.Feedback;
import com.PMS.PMSServer.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {
	@Autowired
	private FeedbackService service;

	@GetMapping("/all")
	public ResponseEntity<?> selectAll() {
		List<Feedback> feed = service.readAllFeedbacks();
		if (feed != null) {
			return ResponseEntity.ok().body(feed);
		}
		return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);

	}

	@GetMapping
	public ResponseEntity<?> selectFeedbackById(@RequestParam int uid) throws Exception {
		List<Feedback> feed = service.getFeedbackByUserId(uid);
		return ResponseEntity.ok().body(feed);

	}

	@PostMapping
	public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback, @RequestParam int uid,
			@RequestParam int pid) throws Exception {
		Feedback feed;
		feed = service.createFeedBack(uid, pid, feedback);
		return ResponseEntity.status(HttpStatus.CREATED).body(feed);

	}
	
	
	@PutMapping
	public ResponseEntity<?> updateFeedbackAckStatus(@RequestParam int fid, @RequestParam String status) throws SQLException
	{
		boolean res = service.updateFeedbackAckStatus(fid, status);
		return ResponseEntity.ok(Map.of("status" , "Feedback Status Updated!"));
	}

}
