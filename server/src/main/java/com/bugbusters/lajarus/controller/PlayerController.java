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
import org.springframework.web.bind.annotation.RequestParam;

/* 
    Description: This Controller is receiving data from requests of the client
    and with the support of Services updating, creating or deleting Quests from the Database.
    It is supported with the spring framework.
*/

@RestController
@RequestMapping(value = "/player")
@Api(name = "Player managment API", description = "Manage players")
@ApiAuthToken()
public class PlayerController {

    //The Service is the midlman that connects Controller with the Database
    @Autowired
    private PlayerService playerService;
    
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    /*
        This method Controller is returning to the client all the players
        Warning: If there are too many players inside of teh database, then
        returning them all may cause many problems.
        ToDo:A Better to switch with a method that will request a specific number
        of data.
    */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all players")
    public List<PlayerEntity> findAll() {
        return playerService.findAll();
    }
    
    /*
        This Controller is returning the player informations with a specific given
        @param name.
    */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get player by name")
    public PlayerEntity findPlayerByName(
            @ApiPathParam(name = "name", description = "The player's name")
            @PathVariable String name)
            throws Exception {
        return playerService.findPlayerByName(name);
    }
    /*
        This Controller is returning a list of players that are near to the current
        player  who requested to the server.
    */
    @RequestMapping(value = "/near/{name}", method = RequestMethod.GET)
    @ApiMethod(description = "Get players near the requested player")
    public List<PlayerEntity> findPlayersNearby(
            @ApiPathParam(name = "name",
                    description = "The player's name who makes the request")
            @PathVariable String name) throws Exception {
        return playerService.getNearbyPlayers(name);
    }
    /*
        This controller is requested for posting the new positions of
        the current player's position on the database.
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
    
    /*
        THis controller is requested to post (create) a new player, with @params request,
        @param playerEntity
        @RequestBody Annotation is from spring framework witch is affecting the architecture
        of requesting
    */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createPlayer(HttpServletRequest request,
            @RequestBody PlayerEntity playerEntity) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        playerService.createPlayerForUser(playerEntity, username);
    }
    /*
        Every player has got inventory and inside of this has got a items.
        Every player can have got more than 1 item in the inventory.
        This controller adds a new item on the inventory.
    */
    @RequestMapping(value = "/addItem", method = RequestMethod.PUT)
    public void addItem(HttpServletRequest request,
            @RequestBody PlayerItemForm playerItem) {
        playerService.addItem(playerItem.getPlayerId(), playerItem.getItemId());
    }
    /*
        Every player has got inventory and inside of this has got a items.
        Every player can have got more than 1 item in the inventory.
        This controller removes an item from the inventory.
    */
    @RequestMapping(value = "/removeItem", method = RequestMethod.DELETE)
    public void removeItem(HttpServletRequest request,
            @RequestBody PlayerItemForm playerItem) {
        playerService.removeItem(playerItem.getPlayerId(), playerItem.getItemId());
    }
    /*
        There can be many players on the Database but less of them are online.
        This Controller is requesting to put online a player who logged into the
        game.
    */
    @RequestMapping(value = "/online", method = RequestMethod.PUT)
    public void setOnline(HttpServletRequest request,
            @RequestBody PlayerOnlineForm playerOnline) {
	playerService.setOnline(playerOnline.isOnline(), playerOnline.getPlayerId());
    }
}
