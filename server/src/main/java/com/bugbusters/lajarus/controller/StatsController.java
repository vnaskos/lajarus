package com.bugbusters.lajarus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.StatsEntity;
import com.bugbusters.lajarus.service.StatsService;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/stats")
public class StatsController {

    @Autowired
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @RequestMapping(value = "/id/{player}", method = RequestMethod.GET)
    public StatsEntity getStatsById(@PathVariable("player") long player) throws Exception {
        System.out.println(player);
        return statsService.getStatsByPlayerId(player);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createStats(
            @RequestBody StatsEntity statsEntity, Errors errors) throws Exception {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }

        statsService.saveStats(statsEntity);
        return ResponseEntity.ok(statsEntity);
    }

    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteStats(@PathVariable long id) {
        statsService.deleteStats(id);
    }

}
