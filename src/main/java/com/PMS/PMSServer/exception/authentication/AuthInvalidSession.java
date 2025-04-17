package com.PMS.PMSServer.exception.authentication;

public class AuthInvalidSession extends AuthException {
	
	public AuthInvalidSession() {
		super("Invalid Session code. User may be logged out." );
	}
	
	
	
	public AuthInvalidSession(String message) {
		super("Invalid Session code " + message);
	}
}
