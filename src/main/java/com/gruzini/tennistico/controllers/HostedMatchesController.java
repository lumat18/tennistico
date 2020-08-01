package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.matchDto.HostedMatchDto;
import com.gruzini.tennistico.services.dto_related.MatchDtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/hosted")
public class HostedMatchesController {
    private static final String INITIAL_FILTER = "lvl";

    private final MatchDtoService matchDtoService;

    public HostedMatchesController(MatchDtoService matchDtoService) {
        this.matchDtoService = matchDtoService;
    }

    @GetMapping
    public String showUpcomingMatchesPage(@ModelAttribute(name = "filter") String filter, final Model model, final Principal principal) {
        if (isNull(filter) || filter.isBlank()){
            filter = INITIAL_FILTER;
        }
        final List<HostedMatchDto> allHostedMatches = matchDtoService.getHostedMatchDtosExceptHostedBy(filter, principal.getName());
        model.addAttribute("hostedMatches", allHostedMatches);
        return "hosted";
    }

    @PostMapping
    public String processMatchFilter(@RequestParam(name = "filter") final String filter, final RedirectAttributes attributes, final Principal principal){
        attributes.addFlashAttribute("filter", filter);
        return "redirect:/hosted";
    }
}
