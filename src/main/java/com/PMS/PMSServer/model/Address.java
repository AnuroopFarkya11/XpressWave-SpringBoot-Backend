package com.PMS.PMSServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private int addressId;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private AddressType addressType;

}
