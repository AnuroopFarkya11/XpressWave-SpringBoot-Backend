//package com.PMS.PMSServer.util;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//
//import com.PMS.PMSServer.exception.authentication.AuthInvalidSession;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@Component
//public class SessionValidationFiltter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
//			throws IOException, ServletException,AuthInvalidSession {
//		
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		
//		
//		
////		response.setHeader("Access-Control-Allow-Origin", "*");
////		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
////		response.setHeader("Access-Control-Allow-Headers", "Origin,Content-Type, Accept, Authorization, session-code");
////		response.setHeader("Access-Control-Allow-Credentials", "true");
//		
//		
//		
//		String path = request.getRequestURI();
//		
//		if(path.startsWith("/auth"))
//		{
//			chain.doFilter(req, resp);
//			return;
//		}
//		
//		HttpSession session = request.getSession(false);
//		String clientSessionCode = request.getHeader("session-code");
//		
//		if(session!=null && SessionUtil.isLoggedIn(session))
//		{
//			chain.doFilter(req, resp);
//			return;
//		}
//		
//		
//		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		response.setContentType("application/json");
//		response.getWriter().write("{\"error\" : \"Unauthorized\", \"message\" : \"Invalid Session\"}");
//		
////		throw new AuthInvalidSession();
//		
//	}
//	
//}
