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
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthToken;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.validation.Errors;

/**
 * This Controller is receiving data from requests of the client,
 * with the support of QuestService it updates, creates or deletes
 * Quests from the Database.
 * 
 * @author karen
 */
@RestController
@RequestMapping(value = "/quest")
@Api(name = "Quest Managment Controller",
        description = "Provides methods for quest managment (CRUD)",
        stage = ApiStage.RC)
@ApiAuthToken()
public class QuestController {
    
    @Autowired
    private QuestService questService;
    
    @Autowired
    private QuestValidator questValidator;
    
    /**
     * Retrieve quest by id
     * 
     * @param id quest id
     * @return Quest
     * @throws Exception 
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Retrieve a specific quest")
    public QuestEntity getQuestById(
            @ApiPathParam(name = "id", description = "Quest id")
            @PathVariable long id) throws Exception {
        return questService.getQuestById(id);
    }
    
    /**
     * Get quests near a given location
     * The location is retrieved by the player's profile
     * 
     * @param name player name
     * @return List of Quests
     * @throws Exception 
     */
    @RequestMapping(value = "/near/player/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get quests near a given player's location")
    public List<QuestEntity> getNearbyQuest(
            @ApiPathParam(name = "name",
                    description = "The player's name to get the location from")
            @PathVariable String name) throws Exception {
        return questService.getNearbyQuests(name);
    }
    
    /**
     * Get all quests 
     * Highly not recommended for production use
     * 
     * @return List of Quests
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Retrieve all quests (only for debugging reasons)")
    public List<QuestEntity> getAll() {
        return questService.getAll();
    }
    
    /**
     * Create a new quest
     * 
     * @param questEntity details about the new Quest
     * @param errors
     * @return HTTP codes (200 for success)
     * @throws Exception 
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiMethod(description = "Create a new quest")
    public ResponseEntity<?> createQuest(
            @ApiPathParam(name = "questEntity",
                    description = "Object with details about the new Quest")
            @RequestBody QuestEntity questEntity,
            Errors errors) throws Exception {
        questValidator.validate(questEntity, errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(ValidationErrorBuilder
                            .fromBindingErrors(errors));
        }
        questService.createQuest(questEntity);
        return ResponseEntity.ok(questEntity);
    }
    
    /**
     * Delete a quest by id
     * 
     * @param id Quest id
     */
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Delete quest by id")
    public void deleteQuest(
            @ApiPathParam(name = "id", description = "Quest id")
            @PathVariable long id) {
        questService.deleteQuest(id);
    }
}
