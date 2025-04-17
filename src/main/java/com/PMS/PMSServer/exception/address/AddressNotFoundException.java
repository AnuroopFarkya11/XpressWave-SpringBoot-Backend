package com.PMS.PMSServer.exception.address;

public class AddressNotFoundException extends AddressException{
	public AddressNotFoundException() {
		super("Address not found");
	}

	public AddressNotFoundException(int id) {
		super("Address not found with ID :" + id);
	}
}
