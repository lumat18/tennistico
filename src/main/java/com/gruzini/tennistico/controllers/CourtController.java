package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.forms.CourtForm;
import com.gruzini.tennistico.services.dto_related.CourtDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courts")
@Slf4j
public class CourtController {
    @GetMapping
    public String showCourtsPage(final Model model) {
        model.addAttribute("courtForm", new CourtForm());
        return "courts";
    }
}
