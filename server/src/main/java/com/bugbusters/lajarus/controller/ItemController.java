package com.bugbusters.lajarus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugbusters.lajarus.entity.ItemEntity;
import com.bugbusters.lajarus.service.ItemService;



@RestController
@RequestMapping(value = "/item")
public class ItemController {
    
    
    private final ItemService itemService;
    
   
     @Autowired
    public ItemController( ItemService itemService ) 
    {
        this.itemService = itemService;
    }
    
    
     @RequestMapping( value = "/id/{id}", method = RequestMethod.GET )
    public ItemEntity getItemById( @PathVariable long id ) throws Exception
    {
        return itemService.getItemById( id );
    }
    
    
     @RequestMapping( value = "/type/{type}", method = RequestMethod.GET )
    public ItemEntity getItemByType( @PathVariable String type ) throws Exception
    {
        return itemService.getItemByType( type );
    }
    
    
     @RequestMapping(value = "/create/id/{id}/name/{name}/description/{description}/type/{type}/value/{value}/price/{price}", method = RequestMethod.POST)
    public void createQuest( @PathVariable long id, @PathVariable String name, @PathVariable String description, @PathVariable String type, @PathVariable long value, @PathVariable long price ) throws Exception
    {
        itemService.createItem(id, name,description, type, value, price);
    }
    
    
     @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.POST)
    public void deleteItem( @PathVariable long id ) {
        itemService.deleteItem( id );
    }
    
}
