package com.PMS.PMSServer.services;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.PMS.PMSServer.config.TestDBConfig;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import com.PMS.PMSServer.model.Feedback;
import com.PMS.PMSServer.model.FeedbackAckStatus;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.PMSUserRole;
import com.PMS.PMSServer.service.FeedbackService;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.Utils;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import({TestDBConfig.class})
public class FeedbackServiceTest {

	private FeedbackService feedbackService;
	private UserService userService;
	public FeedbackServiceTest() {
		this.feedbackService=new FeedbackService();
		this.userService=new UserService();
	}
	
	
	@Test()
	public void testInsertFeedback() {

		System.out.println("I am testing Add Feedback functionality");
		boolean flag = false;
		try {
			int feedbackId=Utils.generateRandomId();
			PMSUser user= userService.getUser(9);
			int parcelId=55511;
			String feedbackString="Anuroop is ddoing very very well";
			Feedback feedback =new Feedback(feedbackId,user.getName(),feedbackString,4.5,FeedbackAckStatus.BLANK,LocalDateTime.now());
			Feedback Insertedfeedback = feedbackService.createFeedBack(9, parcelId,feedback);
			if(Insertedfeedback!=null) {
				flag=false;
			}
			assertNull(Insertedfeedback);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		     flag=true;;
		}
		
		assertEquals(flag, true);

	}

	@Test()
	public void testGetFeedbackByUserId() {

		System.out.println("I am testing Get Feedback By User Id functionality");
		boolean flag = false;
		try {
			
			List<Feedback> newFeedback = feedbackService.getFeedbackByUserId(9);
			if(newFeedback!=null) {
				flag=true;
			}
			assertNotNull(newFeedback);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);

	}
	
	@Test()
	public void testGetAllFeedbacks() {

		System.out.println("I am testing Get All Feedbacks functionality");
		boolean flag = false;
		try {
			
			List<Feedback> allFeedbacks = feedbackService.readAllFeedbacks();
			if(allFeedbacks!=null) {
				flag=true;
			}
			assertNotNull(allFeedbacks);
			
		} catch (Exception e) {

			e.printStackTrace();
			
		
		}
		
		assertEquals(flag, true);

	}
}
