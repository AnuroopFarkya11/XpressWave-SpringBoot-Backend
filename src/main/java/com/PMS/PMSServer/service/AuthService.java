package com.PMS.PMSServer.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.PMS.PMSServer.exception.user.UserNotFoundException;
import com.PMS.PMSServer.model.AuthCredentials;
import com.PMS.PMSServer.model.PMSUser;
import com.PMS.PMSServer.model.SessionResponse;
import com.PMS.PMSServer.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

	private UserService userService;

	public AuthService()
	{
		this.userService = new UserService();
	}
	
	
	
	public SessionResponse login(AuthCredentials credentials, HttpSession session) throws Exception {

		try {
			PMSUser user = userService.getUserByEmailNPassword(credentials.getEmail(), credentials.getPassword());

			if (user != null) {
				SessionUtil.createSession(session, user.getId());
				return new SessionResponse(user.getId(),"Login Success", SessionUtil.getSessionId(session));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		throw new UserNotFoundException(credentials.getEmail());
	}

	public SessionResponse register(PMSUser user, HttpSession session) throws Exception {
		try {
			PMSUser userDB = userService.addUser(user);
			if (userDB != null) {
				SessionUtil.createSession(session, user.getId());
				return new SessionResponse(user.getId(),"User Created Successfully!", SessionUtil.getSessionId(session));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	
	public SessionResponse logout(HttpSession session) throws IllegalStateException
	{
		SessionUtil.destroySession(session);
		return new SessionResponse(1,"User Session Cleared","");
	}

}
