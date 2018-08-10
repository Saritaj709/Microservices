package com.bridgelabz.microservices.note.interceptor;

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
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object object) {
		
		String token=request.getHeader("Authorization");
		if(!token.isEmpty()) {
		request.setAttribute("userId",jwtToken.parseJwtToken(token));
			return true;
		}
		return false;
	}
	
}
