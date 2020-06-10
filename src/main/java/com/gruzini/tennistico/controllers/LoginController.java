package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.forms.UserLoginForm;
import com.gruzini.tennistico.models.forms.UserRegistrationForm;
import com.gruzini.tennistico.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
   private final LoginService loginService;

   public LoginController(final LoginService loginService) {
      this.loginService = loginService;
   }

   @GetMapping
   public String showFirstRegistrationStep(final Model model) {
      model.addAttribute("userLoginForm", new UserLoginForm());
      return "login";
   }

   @PostMapping("/step-one")
   public String processFirstRegistrationStep(@Valid @ModelAttribute final UserLoginForm userLoginForm) {
      loginService.validateCredentials(userLoginForm);
      return "redirect:/dashboard";
   }
}
