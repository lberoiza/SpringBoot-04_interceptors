package com.springboot.attention_time.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

  @Value("${config.hour.open}")
  private Integer openHour;

  @Value("${config.hour.close}")
  private Integer closeHour;

  @GetMapping({ "", "/", "/index" })
  public String index(Model model) {
    model.addAttribute("title", "Wellcome User");
    return "index";
  }

  @GetMapping("/close")
  public String close(Model model) {
    model.addAttribute("title", "Sorry, we are close");
    String message = String.format("Please visit us between %d and %d", openHour, closeHour);
    model.addAttribute("message", message);
    return "close";
  }

}
