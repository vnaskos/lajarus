/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.controller;

import com.bugbusters.lajarus.AbstractControllerTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author Karen
 * 
 */
public class QuestControllerTest extends AbstractControllerTest {
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void getQuestsTest() throws Exception {
        String uri = "/quest/all";
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
    public void getQuestByIdTest() throws Exception {
         String uri = "/quest/id/1";
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
    public void getNearbyQuestTest() throws Exception {
        String uri = "/quest/near/name";
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
    public void createQuestTest() throws Exception {
        String uri = "quest/create/name/location/36.4/37.5/description";
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
    public void deleteQuestTest() throws Exception {
        String uri = "/quest/delete/id/1";
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
    
}
