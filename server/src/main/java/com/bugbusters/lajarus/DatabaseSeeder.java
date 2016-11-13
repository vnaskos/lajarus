package com.bugbusters.lajarus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.repository.PlayerRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private PlayerRepository playerRepository;

    @Autowired
    public DatabaseSeeder(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... arg0) throws Exception {
        List<PlayerEntity> playerEntities = new ArrayList<>();

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(1);
        playerEntity.setName("admin");
        playerEntity.setLatitude(40.2);
        playerEntity.setLongitude(45.4);
        playerEntities.add(playerEntity);

        PlayerEntity playerEntity2 = new PlayerEntity();
        playerEntity2.setId(2);
        playerEntity2.setName("giorgis");
        playerEntity2.setLatitude(40.2003);
        playerEntity2.setLongitude(45.4);
        playerEntities.add(playerEntity2);

        PlayerEntity playerEntity3 = new PlayerEntity();
        playerEntity3.setId(3);
        playerEntity3.setName("user1");
        playerEntity3.setLatitude(40.1);
        playerEntity3.setLongitude(45.0);
        playerEntities.add(playerEntity3);

        playerRepository.save(playerEntities);
    }

}
