package com.bridgelabz.microservices.user.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

	Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestId = UUID.randomUUID().toString();
		request.setAttribute("reqId", requestId);
		logger.info(
				"Before process request is called for " + request.getRequestURI() + " with request id " + requestId);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		String requestId = UUID.randomUUID().toString();
		request.setAttribute("reqId", requestId);
		logger.info("Method executed for " + request.getRequestURI() + " with request id " + requestId);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		String requestId = UUID.randomUUID().toString();
		request.setAttribute("reqId", requestId);
		logger.info("Request completed for " + request.getRequestURI() + " with request id " + requestId);
	}

}
