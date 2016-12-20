package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.repository.QuestRepository;
import com.bugbusters.lajarus.repository.PlayerRepository;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;
    
    @Autowired
    private PlayerRepository playerRepository;

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

    public void createQuest(QuestEntity questEntity) {
        questRepository.saveAndFlush(questEntity);
    }

    public void deleteQuest(long id) {
        questRepository.deleteQuest(id);
    }
    
    public List<QuestEntity> getNearbyQuests(String name) {
        PlayerEntity player = playerRepository.findPlayerByName(name);
        List<QuestEntity> nearbyQuests = questRepository.findNearByQuests(
                player.getLatitude(), player.getLongitude(), 0.2);
        return nearbyQuests;
    }
    
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
