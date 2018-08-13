/*package com.bridgelabz.microservices.note.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bridgelabz.microservices.note.services.Token;

@Component
public class NotesInterceptor implements HandlerInterceptor {
	
	@Autowired
	Token jwtToken;
		 
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object object) throws Exception {
		String token = request.getHeader("token");
		System.out.println("token "+token);
		if(token!=null) {
		String userId = jwtToken.parseJwtToken(token);
		System.out.println(userId);			
			request.setAttribute("userId",userId);
			return true;
			
		}
		return false;
	}
	
}
*/