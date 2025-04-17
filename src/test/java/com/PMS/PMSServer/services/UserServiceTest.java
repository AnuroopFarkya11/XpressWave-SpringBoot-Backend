package com.PMS.PMSServer.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.PMS.PMSServer.config.TestDBConfig;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.PMSUserRole;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.Utils;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({TestDBConfig.class})
public class UserServiceTest {

	private UserService userService;

	public UserServiceTest() {
		this.userService = new UserService();
	}

//	@Test()///expected = Exception.class
//	@Order(1)
//	public void testInsertUser() {
//
//		System.out.println("I am testing insert user functionality");
//		PMSUser user = new PMSUser(15, "Umerdjd","umelrf77j@gmail.com","Umerp@12","7722334455",PMSUserRole.ADMIN);
//		boolean flag = false;
//		try {
//			PMSUser dbuser=userService.addUser(user);
//			if(user!=null) {
//				flag = true;
//			}
//			
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			
//		     flag=false;
//		}
//		
//		assertEquals(flag, true);
//
//	}
	@Test()//expected = Exception.class
//    @Order(2)
	public void testGetUser() {

		System.out.println("I am testing Get user functionality");
		boolean flag = false;
		try {
			
			PMSUser user = userService.getUser(9);
			if(user!=null) {
				flag=true;
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);

	}
	@Test()//expected = Exception.class
//  @Order(2)
	public void testUpdateUser() {

		System.out.println("I am Testing  Update User functionality");
		boolean flag = false;
		try {
			PMSUser user =userService.getUser(9);
			String randomName=""+Utils.generateRandomId();
			user.setName("");
			PMSUser UpdatedUser = userService.updateUser(9,user);
			if(user!=null) {
				flag=true;
			}
			assertEquals(randomName,UpdatedUser.getName());
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);

	}
	
	@Test()
	public void testGetAllUsers() {
		System.out.println("I am Testing  Get All  Users functionality");
		boolean flag = false;
		try {
			
			List<PMSUser> users = userService.getAllUsers();
			if(users!=null) {
				flag=true;
			}
			assertNotNull(users);
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);
	}
	
	@Test()
	public void testGetUserBYEmailNPassword() {

		System.out.println("I am testing Get user By Email and Password functionality");
		boolean flag = false;
		
		try {
			String email="anuroopf77@gmail.com";
			String password="Anuroop@12";
			
			PMSUser user = userService.getUserByEmailNPassword(email, password);
			if(user!=null) {
				flag=true;
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);

	}
}
