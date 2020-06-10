package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.ActivationToken;
import com.gruzini.tennistico.services.ActivationService;
import com.gruzini.tennistico.services.ActivationTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activate")
public class ActivationController {
    private static final String ACCOUNT_ACTIVATED_MESSAGE = "Your account is now activated";

    private final ActivationTokenService activationTokenService;
    private final ActivationService activationService;

    public ActivationController(ActivationTokenService activationTokenService, ActivationService activationService) {
        this.activationTokenService = activationTokenService;
        this.activationService = activationService;
    }

    @GetMapping
    public String handleAccountActivation(@RequestParam(name = "value") final String tokenValue, final Model model) {
        final ActivationToken token = activationTokenService.findTokenByValue(tokenValue);
        activationService.activate(token.getUser());
        model.addAttribute("message", ACCOUNT_ACTIVATED_MESSAGE);
        return "login";
    }
}
