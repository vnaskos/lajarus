package com.bugbusters.lajarus.test;

import com.bugbusters.lajarus.entity.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andreas on 19-Dec-16.
 */

public class entity_User_test {
    User.Builder builder = new User.Builder();
    User user = new User(builder);

    @Test
    public void setUsername_getUsername_test() {
        user.setUsername("Andreas Mitrousis");
        String result = user.getUsername();
        assertEquals("Andreas Mitrousis",result);
    }

    @Test
    public void setId_getId_test() {
        user.setId(12345);
        long result = user.getId();
        assertEquals(12345,result);
    }

}
