package com.bugbusters.lajarus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.service.QuestService;

@RestController
@RequestMapping(value = "/quest")
public class QuestController {

    @Autowired
    private QuestService questService;

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public QuestEntity getQuestById(@PathVariable long id) throws Exception {
        return questService.getQuestById(id);
    }

    @RequestMapping(value = "/near/player/{name}", method = RequestMethod.GET)
    public List<QuestEntity> getNearbyQuest(@PathVariable String name) throws Exception {
        return questService.getNearbyQuests(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<QuestEntity> getAll() {
        return questService.getAll();
    }

    @RequestMapping(value = "/create/name/{name}/location/{latitude}/{longitude}/description/{description}", method = RequestMethod.POST)
    public void createQuest(@PathVariable String name, @PathVariable long latitude, @PathVariable long longitude, @PathVariable String description) throws Exception {
        questService.createQuest(name, latitude, longitude, description);
    }

    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteQuest(@PathVariable long id) {
        questService.deleteQuest(id);
    }
}
