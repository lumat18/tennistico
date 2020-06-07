package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.User;
import com.gruzini.tennistico.exceptions.EmailAlreadyExistsException;
import com.gruzini.tennistico.models.forms.PlayerRegistrationForm;
import com.gruzini.tennistico.models.forms.UserRegistrationForm;
import com.gruzini.tennistico.mappers.PlayerMapper;
import com.gruzini.tennistico.mappers.UserMapper;
import com.gruzini.tennistico.services.RegistrationService;
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

    private final UserMapper userMapper;
    private final PlayerMapper playerMapper;
    private final RegistrationService registrationService;

    public RegistrationController(UserMapper userMapper, PlayerMapper playerMapper, RegistrationService registrationService) {
        this.userMapper = userMapper;
        this.playerMapper = playerMapper;
        this.registrationService = registrationService;
    }

    @GetMapping("/step-one")
    public String showFirstRegistrationStep(final Model model) {
        model.addAttribute("userRegistrationForm", new UserRegistrationForm());
        return "registration1";
    }

    @PostMapping("/step-one")
    public String processFirstRegistrationStep(@Valid @ModelAttribute final UserRegistrationForm userRegistrationForm, final Errors errors) {
        if(registrationService.isEmailExists(userRegistrationForm.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }
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
            System.out.println("errors in player registration form");
            return "registration2";
        }
        User user = userMapper.toUser(userRegistrationForm);
        Player player = playerMapper.toPlayer(playerRegistrationForm);
        user.setPlayer(player);

        registrationService.register(user);
        sessionStatus.setComplete();
        return  "redirect:/login";
    }
}
