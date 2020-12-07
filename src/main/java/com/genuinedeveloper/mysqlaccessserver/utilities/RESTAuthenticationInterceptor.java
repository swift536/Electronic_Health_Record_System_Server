package com.genuinedeveloper.mysqlaccessserver.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.genuinedeveloper.mysqlaccessserver.MainController;

@Component
public class RESTAuthenticationInterceptor implements HandlerInterceptor {
	
	  @Autowired
	  private Authentication auth;
	  
	  Logger logger = LoggerFactory.getLogger(RESTAuthenticationInterceptor.class);
	
	  @Override
	   public boolean preHandle
	      (HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) 
	      throws Exception {
	      
		  logger.info("Logger entered");
		  
		  char[] creds = servletRequest.getHeader("AUTHORIZATION").toCharArray();
		  
		  boolean response = false;
		  
		  if (auth.headerAuthentication(creds)) {
			  
			  response =  true;
			  
		  }
		  
	      System.out.println("Pre Handle method is Calling, response: " + response);
	      
	      return response;
	   }
	  
	  @Override
	  public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	     Object handler, ModelAndView modelAndView) throws Exception {
	     
	  }
	  
	  @Override
	  public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object 
	     handler, Exception exception) throws Exception {
	     
	  }
	   
}