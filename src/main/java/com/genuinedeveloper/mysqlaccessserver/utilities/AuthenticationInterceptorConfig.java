package com.genuinedeveloper.mysqlaccessserver.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class AuthenticationInterceptorConfig extends WebMvcConfigurerAdapter {
	
   @Autowired
   RESTAuthenticationInterceptor interceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptor);
   }
   
}