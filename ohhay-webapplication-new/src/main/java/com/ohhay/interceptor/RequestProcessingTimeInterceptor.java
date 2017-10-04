package com.ohhay.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RequestProcessingTimeInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(RequestProcessingTimeInterceptor.class);

	private String SYSTEM_STATUS;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("getting web html");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// log.info("Request URL::" +
		// request.getRequestURL().toString() +
		// " Sent to Handler :: Current Time=" + System.currentTimeMillis());
		// we can add attributes in the modelAndView and use that in the view
		// page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// long startTime = (Long) request.getAttribute("startTime");
		// log.info("Request URL::" +
		// request.getRequestURL().toString()+ ":: End Time=" +
		// System.currentTimeMillis());
		// log.info("Request URL::" +
		// request.getRequestURL().toString()+ ":: Time Taken=" +
		// (System.currentTimeMillis() - startTime));
	}

}
