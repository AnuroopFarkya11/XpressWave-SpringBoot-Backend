package com.PMS.PMSServer.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Feedback {
	private int feedbackId;
	private String userName;
	private String feedBack;
	private double rating;
	private FeedbackAckStatus ackStatus;
	private LocalDateTime createdAt;
	
}
