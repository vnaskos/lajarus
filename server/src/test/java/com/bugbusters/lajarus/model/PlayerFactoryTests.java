package com.bugbusters.lajarus.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerFactoryTests {

    @Test
    public void createPlayerFactory() {
        PlayerFactory f = new PlayerFactory();
        assertNotNull(f);
    }

}
