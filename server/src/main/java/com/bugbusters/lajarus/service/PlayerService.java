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

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private JwtUserDetailsServiceImpl userService;

    public List<PlayerEntity> findAll() {
        return playerRepository.findAll();
    }

    public PlayerEntity findPlayerById(long id) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerById(id);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player with id %s was not found!", id));
        }

        return playerEntity;
    }
    
    public PlayerEntity findPlayerByName(String name) throws NoResultException {
        PlayerEntity playerEntity = playerRepository.findPlayerByName(name);

        if (playerEntity == null) {
            throw new NoResultException(
                    String.format("Player %s was not found!", name));
        }

        return playerEntity;
    }
    
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

    public void updatePlayerLocation(String name, String latitude, String longitude) {
        double tmp_long, tmp_lat;
        tmp_lat = Double.parseDouble(latitude);
        tmp_long = Double.parseDouble(longitude);
        playerRepository.updatePlayerLocation(name, tmp_lat, tmp_long);
    }
    
    public void createPlayerForUser(PlayerEntity playerEntity, String username) {
        User u = userService.getUserByUsername(username);
        playerEntity.setUser(u);
        PlayerEntity player = playerRepository.saveAndFlush(playerEntity);
        System.out.println("Player id " + player.getId());
    }
    
    public void addItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().add(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    public void removeItem(long playerId, long itemId) {
        PlayerEntity playerEntity = playerRepository.findPlayerById(playerId);
        ItemEntity itemEntity = itemRepository.getItemById(itemId);
        playerEntity.getInventory().remove(itemEntity);
        playerRepository.saveAndFlush(playerEntity);
    }
    
    public void setOnline(boolean isOnline, long playerId) {
        PlayerEntity player = playerRepository.findOne(playerId);
        player.setOnline(isOnline);
        playerRepository.save(player);
    }

    
}
