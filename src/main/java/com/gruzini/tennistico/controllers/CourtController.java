package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.CourtInfoDto;
import com.gruzini.tennistico.services.CourtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard/courts")
@Slf4j
public class CourtController {

    private final CourtService courtService;

    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    public String showCourtsPage(final Model model) {
        List<CourtInfoDto> allCourts = courtService.getAll();
        model.addAttribute("courts", allCourts);
        return "courts";
    }
}
