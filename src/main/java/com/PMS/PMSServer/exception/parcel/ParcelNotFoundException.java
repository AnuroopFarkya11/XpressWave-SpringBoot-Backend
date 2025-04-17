package com.PMS.PMSServer.exception.parcel;

public class ParcelNotFoundException extends ParcelException {

	public ParcelNotFoundException() {
		super("Parcel not found");
	}

	public ParcelNotFoundException(int id) {
		super("Parcel not found with ID :" + id);
	}

}
