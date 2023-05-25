package com.springboot.attention_time.app.interceptors;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Primary
@Component("OpenTimeInterceptor")
public class OpenTimeInterceptor implements HandlerInterceptor {

  @Value("${config.hour.open}")
  private Integer openHour;

  @Value("${config.hour.close}")
  private Integer closeHour;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    Calendar calendar = Calendar.getInstance();
    Integer currentHour = calendar.get(Calendar.HOUR_OF_DAY);

    if (currentHour >= openHour && currentHour < closeHour) {
      String message = String.format(
          "Welcome to the Attention time. We can respond all your question between %d and %d", openHour, closeHour);
      request.setAttribute("message", message);
      return true;
    }

    response.sendRedirect(request.getContextPath().concat("/close"));
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    if (handler instanceof HandlerMethod && modelAndView != null) {
      modelAndView.addObject("message", request.getAttribute("message"));
    }
  }

}
