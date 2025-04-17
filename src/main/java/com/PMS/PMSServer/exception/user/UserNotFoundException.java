package com.PMS.PMSServer.exception.user;

public class UserNotFoundException extends UserException {

	public UserNotFoundException() {
		super("User not found");
	}

	public UserNotFoundException(int id) {
		super("User not found with ID :" + id);
	}
	public UserNotFoundException(String email) {
		super("User not found with email / password :" + email);
	}

}
