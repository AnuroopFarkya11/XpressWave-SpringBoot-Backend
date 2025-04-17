package com.PMS.PMSServer.util;

import java.util.UUID;

import com.PMS.PMSServer.exception.authentication.AuthInvalidSession;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {

	private static final String USER_ID = "userID";
	private static final String SESSION_CODE = "sessionCode";

	public static boolean isLoggedIn(HttpSession session) throws IllegalArgumentException {

		boolean res = session.getAttribute(SESSION_CODE) != null;
		System.out.println("Session validation result : " + res);
		if(res)
		{
			return true;
		}
		
		throw new AuthInvalidSession();
		
		
	}

	public static void createSession(HttpSession session, int userId) {
		String sessionCode = UUID.randomUUID().toString();
		session.setAttribute("sessionCode", sessionCode);
		session.setAttribute(USER_ID, userId);
	}

	public static String getSessionId(HttpSession session) {
		return session.getAttribute(SESSION_CODE).toString();
	}

	public static int getUserId(HttpSession session) {
		return Integer.parseInt(session.getAttribute(USER_ID).toString());
	}

	public static void destroySession(HttpSession session) throws IllegalStateException {
		try {
			session.invalidate();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
