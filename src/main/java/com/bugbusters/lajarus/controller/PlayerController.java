package com.bugbusters.lajarus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.PlayerItemForm;
import com.bugbusters.lajarus.model.PlayerOnlineForm;
import com.bugbusters.lajarus.security.JwtTokenUtil;
import com.bugbusters.lajarus.service.PlayerService;
import javax.servlet.http.HttpServletRequest;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthToken;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for player management (support of CRUD actions)
 */
@RestController
@RequestMapping(value = "/player")
@Api(name = "Player managment API", description = "Manage players")
@ApiAuthToken()
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    /**
     * Get all players (for debugging reasons)
     * Highly not recommended for production usage
     * 
     * @return List of ALL Players 
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all players (for debugging reasons)")
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }
    
    /**
     * Get a specific player by name
     * 
     * @param name Player name
     * @return a single player
     * @throws Exception 
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get player by name")
    public PlayerEntity findPlayerByName(
            @ApiPathParam(name = "name", description = "The player's name")
            @PathVariable String name)
            throws Exception {
        return playerService.findPlayerByName(name);
    }
    
    /**
     * Get players near to a given location
     * The location is retrieved by the requested
     * player's latitude and longitude
     * 
     * @param name Player who made the request
     * @return List of Players
     * @throws Exception 
     */
    @RequestMapping(value = "/near/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get players near the requested player")
    public List<PlayerEntity> findPlayersNearby(
            @ApiPathParam(name = "name",
                    description = "The player's name who makes the request")
            @PathVariable String name) throws Exception {
        return playerService.getNearbyPlayers(name);
    }
    /**
     * Update current location of a player
     * 
     * @param name Player name
     * @param latitude New latitude
     * @param longitude New longitude
     * @throws Exception 
     */
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
    
    /**
     * Create a new player
     * 
     * @param request
     * @param playerEntity Player details
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createPlayer(HttpServletRequest request,
            @RequestBody PlayerEntity playerEntity) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        playerService.createPlayerForUser(playerEntity, username);
    }
    
    /**
     * Insert item to players inventory
     * 
     * @param request
     * @param playerItem Item to be inserted
     */
    @RequestMapping(value = "/addItem", method = RequestMethod.PUT)
    public void addItem(HttpServletRequest request,
            @RequestBody PlayerItemForm playerItem) {
        playerService.addItem(playerItem.getPlayerId(), playerItem.getItemId());
    }
    
    /**
     * Delete / Remove item from player's inventory
     * 
     * @param request
     * @param playerItem Item to be removed 
     */
    @RequestMapping(value = "/removeItem", method = RequestMethod.DELETE)
    public void removeItem(HttpServletRequest request,
            @RequestBody PlayerItemForm playerItem) {
        playerService.removeItem(playerItem.getPlayerId(), playerItem.getItemId());
    }
    
    /**
     * Update players online / availability status
     * 
     * @param request
     * @param playerOnline Player id and online status
     */
    @RequestMapping(value = "/online", method = RequestMethod.PUT)
    public void setOnline(HttpServletRequest request,
            @RequestBody PlayerOnlineForm playerOnline) {
	playerService.setOnline(playerOnline.isOnline(), playerOnline.getPlayerId());
    }
}
