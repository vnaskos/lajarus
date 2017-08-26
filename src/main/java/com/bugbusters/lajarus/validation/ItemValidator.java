/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.validation;

import com.bugbusters.lajarus.entity.ItemEntity;
import com.bugbusters.lajarus.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Karen
 */
@Component
public class ItemValidator implements Validator {

    @Autowired
    private ItemService itemService;
    
    @Override
    public boolean supports(Class<?> type) {
        return ItemEntity.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        ItemEntity itemEntity = (ItemEntity) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        
        if ( itemEntity.getName().length() < 4 || itemEntity.getName().length() > 50 )
        {
            errors.rejectValue("name", "Size.item.name",
                    "Name must be 4-50 characters long");
        }
        
        if ( itemService.isThereItemtByName(itemEntity.getName()) )
        {
             errors.rejectValue("name", "Existed.item.name",
                    "This name of the item exists");
        }
        
        ValidationUtils.rejectIfEmpty(errors, "type", "type.empty");
        if ( itemEntity.getType().length() < 4 || itemEntity.getType().length() > 50 )
        {
            errors.rejectValue("type", "Size.item.type",
                    "Type must be 4-50 characters long");
        }
        
        ValidationUtils.rejectIfEmpty(errors, "value", "value.empty");
        if ( itemEntity.getValue() < 0 )
        {
            errors.rejectValue("value", "Negetive.value",
                    "Value can not be negative");
        }
        
        ValidationUtils.rejectIfEmpty(errors, "price", "price.empty");
        if ( itemEntity.getPrice() < 0 )
        {
            errors.rejectValue("price", "Negetive.price",
                    "Price can not be negative");
        }
    }
    
}
