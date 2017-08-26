package com.bugbusters.lajarus.controller;

import com.bugbusters.lajarus.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vasilis Naskos
 */
@Transactional
public class PlayerControllerTest extends AbstractControllerTest {
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void getPlayersTest() throws Exception {
        String uri = "/player/all";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
    
    @Test
    public void findAdminPlayerByNameTest() throws Exception {
        String uri = "/player/name/admin";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
    
    @Test
    public void findPlayersNearAdminTest() throws Exception {
        String uri = "/player/nearPlayers/admin";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
    
    @Test
    public void updateAdminLocationTest() throws Exception {
        String uri = "/player/admin/location/34.1234/43.1234";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri).accept(
                        MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body not to have a value",
                content.trim().length() == 0);
    }
    
}
