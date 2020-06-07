package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.forms.PlayerRegistrationForm;
import com.gruzini.tennistico.forms.UserRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@SessionAttributes("userRegistrationForm")
public class RegistrationController {

    @GetMapping("/step-one")
    public String showFirstRegistrationStep(final Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "registration1";
    }

    @PostMapping("/step-one")
    public String processFirstRegistrationStep(@Valid @ModelAttribute final UserRegistrationForm userRegistrationForm, final Errors errors) {
        if (errors.hasErrors()) {
            return "registration1";
        }
        return "redirect:/registration/step-two";
    }

    @GetMapping("/step-two")
    public String showSecondRegistrationStep(final Model model) {
        model.addAttribute("playerRegistrationForm", new PlayerRegistrationForm());
        return "registration2";
    }

    @PostMapping("/step-two")
    public String processSecondRegistrationStep(@Valid @ModelAttribute final PlayerRegistrationForm playerRegistrationForm,
                                                final Errors errors,
                                                @ModelAttribute final UserRegistrationForm userRegistrationForm,
                                                final SessionStatus sessionStatus) {
        if (errors.hasErrors()){
            return "registration2";
        }

        sessionStatus.setComplete();
        return  "login";
    }
}
