package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.ItemEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.repository.ItemRepository;
import com.bugbusters.lajarus.repository.PlayerRepository;
import com.bugbusters.lajarus.security.entity.User;
import javax.persistence.NoResultException;

/*
    Type: Service
    This Service is the midlman between the PlayerController and the PlayerEntity
    witch is connected with the Database with the support of the spring framework.
    This is receiving the informations from the controller and modifying it for the
    needs of the client applications.
*/

@Service
public class PlayerService {
    
    //An object of PlayerRepository Interface that acces directly to the database
    @Autowired
    private PlayerRepository playerRepository;
    
    //An object of ItemRepository Interface that acces directly to the database
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private JwtUserDetailsServiceImpl userService;
    
    /*
        This Service is returning all the players (list of players) to the client
        who requested via controller.
    */
    public List<PlayerEntity> findAll() {
        return playerRepository.findAll();
    }
    
    /*
        This Service is returning the speicific player with a @param id, if there
        is no a player with this @param id then is retruning an exception
    */
    public PlayerEntity findPlayerById(long id) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerById(id);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player with id %s was not found!", id));
        }

        return playerEntity;
    }
    
    /*
        This Service is responsible for returning to the client who requested for 
        it the player with a spacific @param name.
    */
    public PlayerEntity findPlayerByName(String name) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerByName(name);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player %s was not found!", name));
        }

        return playerEntity;
    }
    
    /*
        This Service is responsible for returning a list of players who are
        near to the current player who requested and called this method of service
    */
    public List<PlayerEntity> getNearbyPlayers(String name) {
        PlayerEntity player = playerRepository.findPlayerByName(name);
        
        if (player == null) {
            throw new NoResultException(
                    String.format("Player %s was not found!", name));
        }
        
        List<PlayerEntity> nearbyPlayers = playerRepository.findNearByPlayers(
                player.getLatitude(), player.getLongitude(), 0.2);
        
        return nearbyPlayers;
    }
    
    /*
        This Service is responsible for updating the new location of player who
        requested it the client via controller
    */
    public void updatePlayerLocation(String name, String latitude, String longitude) {
        double tmp_long, tmp_lat;
        tmp_lat = Double.parseDouble(latitude);
        tmp_long = Double.parseDouble(longitude);
        playerRepository.updatePlayerLocation(name, tmp_lat, tmp_long);
    }
    
    /*
        Every user can have got more than 1 player inside of teh game. So this service
        is resposnible for creating a new player for the users ( for the client ) who
        requested for it.
    */
    public void createPlayerForUser(PlayerEntity playerEntity, String username) {
        User u = userService.getUserByUsername(username);
        playerEntity.setUser(u);
        PlayerEntity player = playerRepository.saveAndFlush(playerEntity);
        System.out.println("Player id " + player.getId());
    }
    
    /*
        Every player has got an inventory. Inside of the inventory can be more than
        1 item. So this service is responsible for adding a new item inside of the
        player's inventory.
    */
    public void addItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().add(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    /*
        TEvery player has got an inventory. Inside of the inventory can be more than
        1 item. So this service is responsible for removing an item from the
        playe's inventory.
    */
    public void removeItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().remove(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    /*
        This service is responsible for seting online an existed player inside of
        the database.
    */
    public void setOnline(boolean isOnline, long playerId) {
        PlayerEntity player = playerRepository.findOne(playerId);
        player.setOnline(isOnline);
        playerRepository.save(player);
    }

    
}
