package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.CourtDto;
import com.gruzini.tennistico.services.dto_related.CourtDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/courts")
@Slf4j
public class CourtController {

    private final CourtDtoService courtDtoService;

    public CourtController(final CourtDtoService courtDtoService) {
        this.courtDtoService = courtDtoService;
    }

    @GetMapping
    public String showCourtsPage(final Model model) {
        List<CourtDto> allCourts = courtDtoService.getAllCourtDto();
        List<String> cities = courtDtoService.getCities();
        model.addAttribute("cities", cities);
        model.addAttribute("foundCourts", allCourts);
        return "courts";
    }
    @PostMapping
    public String processCourtSearch(@RequestParam("selectedCity") final String city, final Model model){
        List<String> cities = courtDtoService.getCities();
        List<CourtDto> foundCourts = courtDtoService.getCourtInfoDtoByCity(city);
        model.addAttribute("cities", cities);
        model.addAttribute("foundCourts", foundCourts);
        return "courts";
    }
}
