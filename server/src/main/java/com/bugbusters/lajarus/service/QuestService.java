package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.repository.QuestRepository;
import com.bugbusters.lajarus.repository.PlayerRepository;


/*
    Type: Service
    This Service is the midlman between the QuestController and the QuestEntity
    with is conected with the Database with the support of the spring framework.
    This is receiving the informations from the controller and modifying it for the
    needs of the client applications.
*/

@Service
public class QuestService {
    
    //An object of QuestRepository Interface that acces directly to the database
    @Autowired
    private QuestRepository questRepository;
    
    
    //An object of PlayerRepository Interface that acces directly to the database
    @Autowired
    private PlayerRepository playerRepository;
    
    
    //This method of the service is returning the quest with a specific @param id
    //Also it is checking if the data that would be returned is NULL or not
    public QuestEntity getQuestById(long id) throws Exception {
        QuestEntity questEntity = questRepository.getQuestById(id);
        
        if (questEntity == null) {
            throw new Exception("Quest " + id + " does not exist");
        }

        return questEntity;
    }
    
    
    public List<QuestEntity> getAll() {
        return questRepository.findAll();
    }
    
    
    //Method of service that creats a new quest on Database
    public void createQuest(QuestEntity questEntity) {
        questRepository.saveAndFlush(questEntity);
    }
    
    
    //Deleting the quest wit ha specific @param id
    public void deleteQuest(long id) {
        questRepository.deleteQuest(id);
    }
    
    
    /*
        A list of quests that are returning to the controller for the quests
        that are near to the player with a specific @param name (the name of the players)
        with the help of geolocation
    */
    public List<QuestEntity> getNearbyQuests(String name) {
        PlayerEntity player = playerRepository.findPlayerByName(name);
        List<QuestEntity> nearbyQuests = questRepository.findNearByQuests(
                player.getLatitude(), player.getLongitude(), 0.2);
        return nearbyQuests;
    }
    
    
    /* 
        A service method that check if there is a quest with a specific @param name
        and returing true or false
    */
    public boolean isThereQuestByName( String name )
    {
        QuestEntity questEntity = questRepository.isThereQuestByName( name );
        if ( questEntity == null )
        {
            return false;
        }
        else
            return true;
    }
    
    
}
