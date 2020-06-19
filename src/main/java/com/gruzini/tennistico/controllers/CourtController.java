package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.models.dto.CourtInfoDto;
import com.gruzini.tennistico.services.dtos.CourtInfoDtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard/courts")
@Slf4j
public class CourtController {

    private final CourtInfoDtoService courtInfoDtoService;

    public CourtController(final CourtInfoDtoService courtInfoDtoService) {
        this.courtInfoDtoService = courtInfoDtoService;
    }

    @GetMapping
    public String showCourtsPage(final Model model) {
        List<CourtInfoDto> allCourts = courtInfoDtoService.getAllCourtDto();
        List<String> cities = courtInfoDtoService.getCities();
        model.addAttribute("cities", cities);
        model.addAttribute("foundCourts", allCourts);
        return "courts";
    }
    @PostMapping
    public String processCourtSearch(@RequestParam("selectedCity") final String city, final Model model){
        List<String> cities = courtInfoDtoService.getCities();
        List<CourtInfoDto> foundCourts = courtInfoDtoService.getCourtInfoDtoByCity(city);
        model.addAttribute("cities", cities);
        model.addAttribute("foundCourts", foundCourts);
        return "courts";
    }
}
