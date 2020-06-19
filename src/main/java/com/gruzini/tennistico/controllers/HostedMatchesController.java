package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import com.gruzini.tennistico.services.dtos.MatchDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard/hosted")
public class HostedMatchesController {
    private final MatchDtoService matchDtoService;

    public HostedMatchesController(MatchDtoService matchDtoService) {
        this.matchDtoService = matchDtoService;
    }

    @GetMapping
    public String showUpcomingMatchesPage(final Model model, final Principal principal) {
        final List<HostedMatchDto> allHostedMatches = matchDtoService.getHostedMatchesDtoExceptHostedBy(principal.getName());
        model.addAttribute("hostedMatches", allHostedMatches);
        return "hosted";
    }
}
