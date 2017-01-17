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

/**
 * Type: Service
 * This Service is the middleman between the PlayerController and the PlayerEntity
 * witch is connected with the Database with the support of the spring framework,
 * receives the information from the controller and modifies it according
 * to the clients needs.
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

    /**
     * Retrieve all players in a list
     * For debugging reasons (Highly not recommended for production code)
     * 
     * @return players
     */
    public List<PlayerEntity> findAll() {
        return playerRepository.findAll();
    }
    
    /**
     * Get a specific player by player ID
     * 
     * @param id
     * @return player
     * @throws NoResultException 
     */
    public PlayerEntity findPlayerById(long id) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerById(id);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player with id %s was not found!", id));
        }

        return playerEntity;
    }
    
    /**
     * Get player by player name
     * 
     * @param name
     * @return player
     * @throws NoResultException 
     */
    public PlayerEntity findPlayerByName(String name) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerByName(name);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player %s was not found!", name));
        }

        return playerEntity;
    }
    
    /**
     * Get players near the current player
     * current player is defined by the name parameter
     * 
     * @param name current player name
     * @return list of players 
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
    
    /**
     * Update the location of the given player
     * 
     * @param name player name
     * @param latitude new latitude value
     * @param longitude new longitude value
     */
    public void updatePlayerLocation(String name, String latitude, String longitude) {
        double newLat, newLong;
        newLat = Double.parseDouble(latitude);
        newLong = Double.parseDouble(longitude);
        playerRepository.updatePlayerLocation(name, newLat, newLong);
    }
    
    /**
     * Create a new player for the current logged in user
     * Logged in means that user has an auth key
     * 
     * @param playerEntity
     * @param username 
     */
    public void createPlayerForUser(PlayerEntity playerEntity, String username) {
        User u = userService.getUserByUsername(username);
        playerEntity.setUser(u);
        PlayerEntity player = playerRepository.saveAndFlush(playerEntity);
        System.out.println("Player id " + player.getId());
    }
    
    /**
     * Insert an item to players inventory
     * 
     * @param playerId
     * @param itemId 
     */
    public void addItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().add(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    /**
     * Remove item from player's inventory
     * 
     * @param playerId
     * @param itemId 
     */
    public void removeItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().remove(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    /**
     * Set players state (online/offline)
     * 
     * @param isOnline
     * @param playerId 
     */
    public void setOnline(boolean isOnline, long playerId) {
        PlayerEntity player = playerRepository.findOne(playerId);
        player.setOnline(isOnline);
        playerRepository.save(player);
    }

    
}
