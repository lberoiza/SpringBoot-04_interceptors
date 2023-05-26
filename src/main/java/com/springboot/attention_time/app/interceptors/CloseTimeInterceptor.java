package com.springboot.attention_time.app.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("CloseTimeInterceptor")
public class CloseTimeInterceptor implements HandlerInterceptor {

  @Value("${config.hour.open}")
  private Integer openHour;

  @Value("${config.hour.close}")
  private Integer closeHour;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    Calendar calendar = Calendar.getInstance();
    Integer currentHour = calendar.get(Calendar.HOUR_OF_DAY);

    if (currentHour >= openHour && currentHour < closeHour) {
      response.sendRedirect(request.getContextPath().concat("/index"));
      return false;
    }

    return true;
  }

}
