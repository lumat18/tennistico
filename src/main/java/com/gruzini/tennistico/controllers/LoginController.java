package com.gruzini.tennistico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
   private final LoginService loginService;

   public LoginController(final LoginService loginService) {
      this.loginService = loginService;
   }

   @GetMapping
   public String showLoginPage() {
      return "login";
   }


}
