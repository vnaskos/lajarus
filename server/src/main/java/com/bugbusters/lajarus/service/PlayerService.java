package com.bugbusters.lajarus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.Player;
import com.bugbusters.lajarus.model.PlayerFactory;
import com.bugbusters.lajarus.repository.PlayerRepository;
import java.util.ArrayList;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerEntity> getAll() {
        return playerRepository.findAll();
    }

    public Player findPlayerByName(String name) throws Exception {
        PlayerEntity playerEntity = playerRepository.findPlayerByName(name);

        if (playerEntity == null) {
            throw new Exception("User " + name + " does not exist");
        }

        return PlayerFactory.create(playerEntity);
    }

    public List<PlayerEntity> findPlayersNearToCurrentPlayer(String name) throws Exception {

        List<PlayerEntity> playersEntity = playerRepository.findAll();
        PlayerEntity currentPlayer = playerRepository.findPlayerByName(name);
        List<PlayerEntity> playersEntity2 = new ArrayList<>();
        double radius = 0.001; // it's about worth up 111 meters http://gis.stackexchange.com/questions/8650/measuring-accuracy-of-latitude-and-longitude
        double center_x = currentPlayer.getLatitude();
        double center_y = currentPlayer.getLongitude();

        for (int i = 0; i < playersEntity.size(); i++) {
            if (isInsideOfRadius(center_x, center_y, playersEntity.get(i).getLatitude(), playersEntity.get(i).getLongitude(), radius)) {
                playersEntity2.add(playersEntity.get(i));
            }
        }
        return playersEntity2;

    }

    public boolean isInsideOfRadius(double center_x, double center_y, double x, double y, double radius) {
//            double tmpX;
//            double tmpY;
//            tmpX = x - center_x;
//            tmpX = Math.pow( tmpX, 2 );
//            tmpY = y - center_y;
//            tmpY = Math.pow( tmpY, 2 );
//            radius = Math.pow( radius, 2 ); 
        if (Math.pow(x - center_x, 2) + Math.pow(y - center_y, 2) < Math.pow(radius, 2)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void updatePlayerLocation(String name,String latitude,String longitude)
    {
        double tmp_long, tmp_lat;
        tmp_lat = Double.parseDouble( latitude );
        tmp_long = Double.parseDouble( longitude );
        playerRepository.updatePlayerLocation( name, tmp_lat, tmp_long );
    }
}
