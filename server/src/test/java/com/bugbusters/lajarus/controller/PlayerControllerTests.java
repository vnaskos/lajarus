package com.bugbusters.lajarus.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.bugbusters.lajarus.entity.PlayerEntity;
import com.bugbusters.lajarus.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlayerControllerTests {

    @Autowired
    PlayerController playerController;

    @Test
    public void createPlayerControllerTest() {
        assertNotNull(playerController);
    }

    @Test
    public void getAllPlayersTest() {
        List<PlayerEntity> players = playerController.getAll();
        assertFalse(players.isEmpty());
    }

    @Test
    public void findPlayerByNameAdminTest() throws Exception {
        Player p = playerController.findPlayerByName("admin");
        assertNotNull(p);
        assertEquals(p.getName(), "admin");
    }

}
