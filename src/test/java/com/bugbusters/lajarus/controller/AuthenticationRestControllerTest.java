package com.bugbusters.lajarus.controller;

import com.bugbusters.lajarus.AbstractControllerTest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Vasilis Naskos
 */
public class AuthenticationRestControllerTest extends AbstractControllerTest {
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void loginAsAdminTest() throws Exception {
        String uri = "/auth/login";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\", \"password\":\"admin\"}")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
    
    @Test
    public void refreshAdminTokenTest() throws Exception {
        String uri = "/auth/login";
        
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\", \"password\":\"admin\"}")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        
        JSONObject jsonResponse = new JSONObject(content);
        String token = jsonResponse.getString("token");
        
        Assert.assertNotNull("failure - expected token not null", token);
        
        uri = "/auth/refresh";
        result = mvc.perform(
                MockMvcRequestBuilders.get(uri).header("Authorization", token)
                        .accept(MediaType.APPLICATION_JSON)).andReturn();
        
        content = result.getResponse().getContentAsString();
        status = result.getResponse().getStatus();
        
        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a value",
                content.trim().length() > 0);
    }
}
