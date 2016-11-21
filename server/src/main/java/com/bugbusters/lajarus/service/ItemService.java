package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.ItemEntity;
import com.bugbusters.lajarus.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ItemService {
    
    
    private final ItemRepository itemRepository;
    
    
    @Autowired
    public ItemService( ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }
    
    
     public ItemEntity getItemById( long id ) throws Exception
    {
        ItemEntity itemEntity = itemRepository.getItemById( id );
        if ( itemEntity == null ) 
        {
            throw new Exception( "Item " + id + " does not exist" );
        }
        
       return itemEntity;
    }
     
      public ItemEntity getItemByType( String type ) throws Exception
    {
        ItemEntity itemEntity = itemRepository.getItemByType( type );
        if ( itemEntity == null ) 
        {
            throw new Exception( "Item " + type + " does not exist" );
        }
        
       return itemEntity;
    }
     
    
       public void createItem( long id, String name, String description, String type, long value, long price )
    {
       
        ItemEntity itemEntity = new ItemEntity();
    
        itemEntity.setID(id);
        itemEntity.setName(name);
        itemEntity.setDescription(description);
        itemEntity.setType(type);
        itemEntity.setValue(value);
        itemEntity.setPrice(price);
        itemRepository.saveAndFlush( itemEntity );
        
    }
 
         public void deleteItem( long id )
    {
        long tmp_id;
        tmp_id = id;
        itemRepository.deleteItem( tmp_id );
    }
       
}
