package com.bugbusters.lajarus;


import org.junit.Test;
import static org.junit.Assert.*;
import com.bugbusters.lajarus.entity.Player;
/**
 * Created by Andreas on 19-Dec-16.
 */

public class entity_player_test {

    Player.Builder builder = new Player.Builder();
    Player item = new Player(builder);

    @Test
    public void setName_getName_test() {
      item.setName("Andreas Mitrousis");
      String result = item.getName();
      assertEquals("Andreas Mitrousis",result);
    }

    @Test
    public void setId_getId_test() {
        item.setId(123456789);
        long result = item.getId();
        assertEquals(123456789,result);
    }
}
