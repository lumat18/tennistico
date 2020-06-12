package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.models.dto.CreatedGameDto;
import com.gruzini.tennistico.services.GameCreationService;
import com.gruzini.tennistico.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/dashboard/create")
public class GameCreationController {

    private final UserService userService;
    private final GameCreationService gameCreationService;

    public GameCreationController(UserService userService, GameCreationService gameCreationService) {
        this.userService = userService;
        this.gameCreationService = gameCreationService;
    }

    @PostMapping("/begin")
    public String beginGameCreation(@RequestParam(name = "courtId") final Long courtId, final Model model) {
        System.out.println("chosen court " + courtId);
        model.addAttribute("chosenCourtId", courtId);
        model.addAttribute("game", CreatedGameDto.builder().build());
        return "create";
    }

    @PostMapping("/process")
    public String processGameCreation(@Valid @ModelAttribute("game") final CreatedGameDto createdGameDto, final Errors errors, final Model model, final Principal principal) {

        if (errors.hasErrors()) {
            model.addAttribute("chosenCourtId", createdGameDto.getCourtId());
            return "create";
        }
        gameCreationService.create(createdGameDto, principal.getName());
        return "dashboard";
    }
}
