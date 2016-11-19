package com.bugbusters.lajarus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.service.PlayerService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthToken;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;

@RestController
@RequestMapping(value = "/player")
@Api(name = "Player managment API", description = "Manage players")
@ApiAuthToken()
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all players")
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get player by name")
    public PlayerEntity findPlayerByName(
            @ApiPathParam(name = "name", description = "The player's name")
            @PathVariable String name)
            throws Exception {
        return playerService.findPlayerByName(name);
    }

    @RequestMapping(value = "/nearPlayers/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get players near the requested player")
    public List<PlayerEntity> findPlayersNearToCurrentPlayer(
            @ApiPathParam(name = "name",
                    description = "The player's name who makes the request")
            @PathVariable String name) throws Exception {
        return playerService.findPlayersNearToCurrentPlayer(name);
    }

    @RequestMapping(value = "/{name}/location/{latitude}/{longitude}",
            method = RequestMethod.POST)
    @ApiMethod(description = "Update player's current location")
    public void updatePlayerLocation(
            @ApiPathParam(name = "name", description = "The player's name")
            @PathVariable String name,
            @ApiPathParam(name = "latitude",
                    description = "The latitude of the player's new location")
            @PathVariable String latitude,
            @ApiPathParam(name = "longitutde",
                    description = "The longitude of the player's new location")
            @PathVariable String longitude)
            throws Exception {
        playerService.updatePlayerLocation(name, latitude, longitude);
    }
}
