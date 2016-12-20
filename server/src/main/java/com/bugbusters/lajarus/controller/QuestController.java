package com.bugbusters.lajarus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.service.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.bugbusters.lajarus.validation.QuestValidator;
import com.bugbusters.lajarus.validation.ValidationErrorBuilder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/* 
    Description: This Controller is receiving data from requests of the client
    and with the support of Services updating, creating or deleting Quests from the Database.
    It is supported with the spring framework.
*/ 


@RestController
@RequestMapping(value = "/quest")
public class QuestController {
    
    //The Service is the midlman that connects Controller with the Database
    @Autowired
    private QuestService questService;
    
    //Validator is checking the validity of data that the server receive
    @Autowired
    private QuestValidator questValidator;
    
    //Responsing the Quest to the client via @param id
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public QuestEntity getQuestById(@PathVariable long id) throws Exception {
        return questService.getQuestById(id);
    }
    
    //The responsing for sending to the client the quests that are near to him via geolocation, @param name
    @RequestMapping(value = "/near/player/{name}", method = RequestMethod.GET)
    public List<QuestEntity> getNearbyQuest(@PathVariable String name) throws Exception {
        return questService.getNearbyQuests(name);
    }
    
    //The Responsing to the client to send to the client all the Quests
    //Warining: If the Quests ara too many on Database maybe will be a crush
    //ToDO: It could be change with a method that will send to the client a specific
    //number of data every time
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<QuestEntity> getAll() {
        return questService.getAll();
    }
    
    //This controller is creating new Quest, @param questEntity, @param errors
    //@RequestMapping(value = "/create/name/{name}/location/{latitude}/{longitude}/description/{description}", method = RequestMethod.POST)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createQuest(@RequestBody QuestEntity questEntity, Errors errors) throws Exception {
        questValidator.validate(questEntity, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }
        questService.createQuest(questEntity);
        return ResponseEntity.ok(questEntity);
    }
    
    //The controller is for deleting the quest with sepcific @param id
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteQuest(@PathVariable long id) {
        questService.deleteQuest(id);
    }
}
