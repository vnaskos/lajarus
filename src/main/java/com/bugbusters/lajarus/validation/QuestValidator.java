/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bugbusters.lajarus.validation;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.service.QuestService;
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
public class QuestValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return QuestEntity.class.equals(type);
    }
    
    @Autowired
    private QuestService questService;

    @Override
    public void validate(Object o, Errors errors) {
        
        QuestEntity questEntity = (QuestEntity) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        if ( questEntity.getName().length() < 4 || questEntity.getName().length() > 50 )
        {
            errors.rejectValue("name", "Size.quest.name",
                    "Name must be 4-50 characters long");
        }
        
        if ( questService.isThereQuestByName( questEntity.getName() ) )
        {
             errors.rejectValue("name", "Existed.quest.name",
                    "This name of the quest exists");
        }
        
        if ( questEntity.getLongitude() == 0 )
        {
            errors.rejectValue("Longitude", "Longitude.isNull", "Longitude can not be null");
        }
        
        if ( questEntity.getLatitude() == 0 )
        {
            errors.rejectValue("Latitude", "Latitude.isNull", "Latitude can not be null");
        }
        
    }
    
}
