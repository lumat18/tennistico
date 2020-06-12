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
        model.addAttribute("courtId", courtId);
        model.addAttribute("game", new CreatedGameDto());
        return "create";
    }

    @PostMapping("/process")
    public String processGameCreation(@Valid @ModelAttribute final CreatedGameDto createdGameDto, final Errors errors, final Model model, final Principal principal) {

        if (errors.hasErrors()) {
            return "create";
        }
        final Player player = userService.getByEmail(principal.getName()).getPlayer();
        gameCreationService.create(createdGameDto, player);
        return "dashboard";
    }
}
