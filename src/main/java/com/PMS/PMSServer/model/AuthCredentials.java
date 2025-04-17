package com.PMS.PMSServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCredentials {
	private String email;
	private String password;
}
