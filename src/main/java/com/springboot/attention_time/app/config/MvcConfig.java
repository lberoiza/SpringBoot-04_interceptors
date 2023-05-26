package com.springboot.attention_time.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
  
  @Autowired
  private HandlerInterceptor openTimeInterceptor;
  
  @Autowired
  @Qualifier("CloseTimeInterceptor")
  private HandlerInterceptor closeTimeInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(openTimeInterceptor).excludePathPatterns("/close");
    registry.addInterceptor(closeTimeInterceptor).excludePathPatterns("", "/", "/index");
  }
  
  
  

}
