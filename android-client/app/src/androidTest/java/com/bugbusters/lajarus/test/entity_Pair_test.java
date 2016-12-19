package com.bugbusters.lajarus.test;

import com.bugbusters.lajarus.entity.Pair;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andreas on 19-Dec-16.
 */

public class entity_Pair_test {
    Pair item = new Pair("MasterKey","ValueExample");

    @Test
    public void setKey_getKey_test() {
        String result = item.getKey();
        assertEquals("MasterKey",result);
    }

    @Test
    public void setValue_getValue_test() {
        String result = item.getValue();
        assertEquals("ValueExample",result);
    }
}
