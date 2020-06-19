package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.forms.PlayerRegistrationForm;
import com.gruzini.tennistico.models.forms.UserRegistrationForm;
import com.gruzini.tennistico.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@SessionAttributes("userRegistrationForm")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/step-one")
    public String showFirstRegistrationStep(final Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "registration1";
    }

    @PostMapping("/step-one")
    public String processFirstRegistrationStep(@Valid @ModelAttribute final UserRegistrationForm userRegistrationForm,
                                               final Errors errors,
                                               final RedirectAttributes attributes) {
        registrationService.validateEmailExistence(userRegistrationForm.getEmail());
        if (errors.hasErrors()) {
            return "registration1";
        }
        attributes.addFlashAttribute("stepTwoPermit", "permit");
        return "redirect:/registration/step-two";
    }

    @GetMapping("/step-two")
    public String showSecondRegistrationStep(final Model model) {
        if(model.getAttribute("stepTwoPermit") == null){
            return "redirect:/registration/step-one";
        }
        model.addAttribute("playerRegistrationForm", new PlayerRegistrationForm());
        return "registration2";
    }

    @PostMapping("/step-two")
    public String processSecondRegistrationStep(@Valid @ModelAttribute final PlayerRegistrationForm playerRegistrationForm,
                                                final Errors errors,
                                                @ModelAttribute final UserRegistrationForm userRegistrationForm,
                                                final SessionStatus sessionStatus,
                                                final RedirectAttributes attributes) {
        if (errors.hasErrors()){
            System.out.println("errors in player registration form");
            return "registration2";
        }

        registrationService.register(userRegistrationForm, playerRegistrationForm);
        sessionStatus.setComplete();
        attributes.addFlashAttribute("stepThreePermit", "permit");
        return  "redirect:/registration/step-three";
    }

    @GetMapping("/step-three")
    public String showThirdRegistrationStep(final Model model) {
        if(model.getAttribute("stepThreePermit") == null){
            return "redirect:/registration/step-two";
        }
        return "registration3";
    }
}
