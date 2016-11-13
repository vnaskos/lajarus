package com.bugbusters.lajarus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.Player;
import com.bugbusters.lajarus.service.PlayerService;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PlayerEntity> getAll() {
        return playerService.getAll();
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Player findPlayerByName(@PathVariable String name) throws Exception {
        return playerService.findPlayerByName(name);
    }

    @RequestMapping(value = "/nearPlayers/{name}", method = RequestMethod.GET)
    public List<PlayerEntity> findPlayersNearToCurrentPlayer(@PathVariable String name) throws Exception {
        return playerService.findPlayersNearToCurrentPlayer(name);
    }

}
