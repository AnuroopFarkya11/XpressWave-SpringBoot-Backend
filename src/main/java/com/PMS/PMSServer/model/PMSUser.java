package com.PMS.PMSServer.model;

import java.util.ArrayList;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class PMSUser {

	public int id;
	private String name;
	private String email;
	private String password;
	private String mobile;
	private List<Address> addresses = new ArrayList<>();
	private PMSUserRole role;
	
	public PMSUser(int id, String name, String email, String password, String mobile,PMSUserRole role)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
	}
	
	public PMSUser(int id, String name, String email, String password, String mobile,PMSUserRole role,List<Address> addresses)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
		this.addresses = addresses;
	}
	
	
	
}
