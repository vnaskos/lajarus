package com.bugbusters.lajarus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.StatsEntity;
import com.bugbusters.lajarus.service.StatsService;
import com.bugbusters.lajarus.validation.UserValidator;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(value = "/stats")
public class StatsController {
    
     @Autowired
    private StatsService statsService;
     
    @Autowired
    private UserValidator userValidator;
     
    public StatsController( StatsService statsService ) 
    {
        this.statsService = statsService;
    }
    
     @RequestMapping( value = "/id/{id}", method = RequestMethod.GET )
    public StatsEntity getStatsById( @PathVariable long id ) throws Exception
    {
        return statsService.getStatsById( id );
    }
    
    
     @RequestMapping( value = "/type/{type}", method = RequestMethod.GET )
    public StatsEntity getStatsByType( @PathVariable String type ) throws Exception
    {
        return statsService.getStatsByType( type );
    }
    
    
    // @RequestMapping(value = "/create/id/{id}/name/{name}/description/{description}/type/{type}/value/{value}/price/{price}", method = RequestMethod.POST)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createStats(@RequestBody StatsEntity statsEntity, Errors errors) throws Exception
    {
        userValidator.validate( statsEntity , errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }
        
        statsService.createStats(statsEntity);
        return ResponseEntity.ok(statsEntity);
    }
    
    
     @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteStats( @PathVariable long id ) {
        statsService.deleteStats( id );
    }
    
}