package com.PMS.PMSServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.PMS.PMSServer.service.AddressService;
import com.PMS.PMSServer.service.FeedbackService;
import com.PMS.PMSServer.service.InvoiceService;
import com.PMS.PMSServer.service.ParcelService;
import com.PMS.PMSServer.service.UserService;
import com.PMS.PMSServer.util.DBUtil;



@SpringBootApplication
@ComponentScan("com.PMS")
public class PmsServerApplication {

	public static void main(String[] args) {

		try {
			
			DBUtil.connectDB();
//			DBUtil.connectDEMODB();

			new UserService().createTable();
			new ParcelService().createTable();
			new AddressService().createTable();
			new FeedbackService().createTable();
			new InvoiceService().createTable();
			
			SpringApplication.run(PmsServerApplication.class, args);
		} catch (Exception e) {
			System.out.println("Oops, Try Again Later.");
			e.printStackTrace();
		}

	}

}
