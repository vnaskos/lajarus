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
 */
public class ItemControllerTest extends AbstractControllerTest {
    
    @Before
    @Override
    public void setUp() {
        super.setUp();
    }
    
    @Test
    public void getItemByIdTest() throws Exception {
        String uri = "/item/all";
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
    public void getItemByTypeTest() throws Exception {
        String uri = "/item/type";
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
    public void createItemTest() throws Exception {
        String uri = "/item/create/id/name/description/type/value/5/price/10";
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
    public void deleteItemTest() throws Exception {
        String uri = "/item/create/id/name/description/type/value/5/price/10";
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
