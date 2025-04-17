package com.PMS.PMSServer.util;

import java.util.Random;

public class Utils {
	
	public static int generateRandomId() {
		Random random = new Random();
		int id = random.nextInt(100000);
		return id;

	}

}
