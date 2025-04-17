package com.PMS.PMSServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SessionResponse {
	
	private int id;
	private String message;
	private String sessionCode;

}
