package com.PMS.PMSServer.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.PMS.PMSServer.config.TestDBConfig;
import com.PMS.PMSServer.model.Address;
import com.PMS.PMSServer.model.AddressType;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.PMSUserRole;
import com.PMS.PMSServer.service.AddressService;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.Utils;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({TestDBConfig.class})
public class AddressServiceTest {

	private AddressService addressService;

	public AddressServiceTest() {
		this.addressService = new AddressService();
	}
	
	
	@Test()
	@Order(1)
	public void testInsertAddress() {

		System.out.println("I am testing insert Address functionality");
		
		boolean flag = false;
		try {
			Integer addressId=Utils.generateRandomId();
			Address address = new Address(addressId,"ITPL street","Bangaluru","Krantaka","560066","India",AddressType.WORK);
			Address dbAddress=addressService.addAddress(75730, address);
			if(dbAddress!=null) {
				flag = true;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=false;
		}
		
		assertEquals(flag, true);

	}
	@Test()
	@Order(2)
	public void testGetFirstAddressByAid() {
		System.out.println("I am testing Get Address By Address Id functionality");
		
		boolean flag = false;
		try {
			Integer addressId=71860;
		//	Address address = new Address(addressId,"ITPL street","Bangaluru","Krantaka","560066","India",AddressType.WORK);
			Address dbAddress=addressService.getAddressByAid(addressId);
			if(dbAddress!=null) {
				flag = true;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=false;
		}
		
		assertEquals(flag, true);

	}
	
	@Test()
	@Order(3)
	public void testGetAllAddresses() {
		System.out.println("I am testing Get All Addresses functionality");
		
		boolean flag = false;
		try {
			
		//	Address address = new Address(addressId,"ITPL street","Bangaluru","Krantaka","560066","India",AddressType.WORK);
			List<Address> dbAddresses=addressService.getAllAddresses();
			if(dbAddresses!=null) {
				flag = true;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=false;
		}
		
		assertEquals(flag, true);

	}
	@Test()
	@Order(4)
	public void testGetAllAddressesByAid(){
		System.out.println("I am testing Get All Addresses By Address Idfunctionality");
		
		boolean flag = false;
		try {
			Integer userId=75730;
		//	Address address = new Address(addressId,"ITPL street","Bangaluru","Krantaka","560066","India",AddressType.WORK);
			List<Address> dbAddresses=addressService.getAllAddressesByUserId(userId);
			if(dbAddresses!=null) {
				flag = true;
			}
			assertNotNull(dbAddresses);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=false;
		}
		
		assertEquals(flag, true);

	}
	
	@Test()
	@Order(5)
	public void testUpdateAddressByAid() {
		System.out.println("I am testing Update Address By Address Id functionality");
		
		boolean flag = false;
		try {
			Integer addressId=71860;
			Address address=addressService.getAddressByAid(addressId);
			address.setCity("Delhi");
			
		//	Address address = new Address(addressId,"ITPL street","Bangaluru","Karantaka","560066","India",AddressType.WORK);
			Address dbAddress=addressService.updateAddress(addressId,address);
			if(dbAddress!=null) {
				flag = true;
			}
		assertEquals("Delhi",dbAddress.getCity());
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=false;
		}
		
		assertEquals(flag, true);

	}
	
	
	
}
