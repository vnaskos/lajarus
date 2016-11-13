package com.bugbusters.lajarus.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTests {

    @Test
    public void createPlayerTest() {
        Player p = new Player("testPlayer", 10.3, 22.4);
        assertNotNull(p);
    }

    @Test
    public void setPlayerNameTest() {
        Player p = new Player("testPlayer", 10.3, 22.4);
        assertEquals(p.getName(), "testPlayer");

        p.setName("testUser");
        assertEquals(p.getName(), "testUser");
    }

    @Test
    public void setPlayerLatitudeTest() {
        Player p = new Player("testPlayer", 10.3, 22.4);
        assertEquals(p.getLatitude(), 10.3, 0.001);

        p.setLatitude(20.3);
        assertEquals(p.getLatitude(), 20.3, 0.001);
    }

    @Test
    public void setPlayerLongitudeTest() {
        Player p = new Player("testPlayer", 10.3, 22.4);
        assertEquals(p.getLongitude(), 22.4, 0.001);

        p.setLongitude(20.3);
        assertEquals(p.getLongitude(), 20.3, 0.001);
    }

    @Test
    public void playerToStringTest() {
        Player p = new Player("testPlayer", 10.3, 22.4);
        assertEquals(p.toString(), "testPlayer (10.3,22.4)");
    }

}
