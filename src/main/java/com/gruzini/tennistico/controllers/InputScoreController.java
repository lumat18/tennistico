package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.mappers.ScoreMapper;
import com.gruzini.tennistico.models.dto.ScoreDTO;
import com.gruzini.tennistico.models.dto.SetDTO;
import com.gruzini.tennistico.services.InputScoreService;
import com.gruzini.tennistico.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/input-score")
public class InputScoreController {

    private final InputScoreService inputScoreService;
    private final MatchService matchService;
    private final ScoreMapper scoreMapper;

    public InputScoreController(final InputScoreService inputScoreService,
                                final MatchService matchService,
                                final ScoreMapper scoreMapper) {
        this.inputScoreService = inputScoreService;
        this.matchService = matchService;
        this.scoreMapper = scoreMapper;
    }

//    @PostMapping("/begin")
    @GetMapping("/{match_id}")
    public String beginSubmittingScore(/*@RequestParam(name = "matchId")*/ @PathVariable(name = "match_id") final Long matchId, final Model model) {
        model.addAttribute("match", matchService.getById(matchId));
        model.addAttribute("score", ScoreDTO.builder().setDtoList(prepareListOfFiveNewSetDto()).build());
        return "score";
    }

    @PostMapping("/process")
    public String processSubmittedScore(@ModelAttribute final Long matchId,
                             @Valid @ModelAttribute(name = "score") final ScoreDTO scoreDTO,
                             final Errors errors) {
        if (errors.hasErrors()) {
            return "score";
        }

        final String score = scoreMapper.mapScoreToString(scoreDTO);
        inputScoreService.inputScore(matchId, score);

        return "redirect:/dashboard";
    }

    private List<SetDTO> prepareListOfFiveNewSetDto(){
        List<SetDTO> setList = new ArrayList<>();
        for(int num = 0; num < 5; num++){
            setList.add(new SetDTO());
        }
        return setList;
    }
}
