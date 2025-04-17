package com.PMS.PMSServer.config;

import org.springframework.boot.test.context.TestConfiguration;

import com.PMS.PMSServer.service.AddressService;
import com.PMS.PMSServer.service.FeedbackService;
import com.PMS.PMSServer.service.ParcelService;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.DBUtil;

import jakarta.annotation.PostConstruct;

@TestConfiguration
public class TestDBConfig {

	@PostConstruct
	void setUpDb() throws Exception {
		try {

			DBUtil.connectDB();
//			DBUtil.connectDEMODB();
			new UserService().createTable();
			new ParcelService().createTable();
			new AddressService().createTable();
			new FeedbackService().createTable();

		} catch (Exception e) {
			System.out.println("Oops, Try Again Later.");
			e.printStackTrace();
			throw e;
		}
	}

}
