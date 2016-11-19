package com.bugbusters.lajarus.service;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.repository.QuestRepository;
import com.bugbusters.lajarus.repository.PlayerRepository;
import java.util.ArrayList;

@Service
public class QuestService {
    
    private final QuestRepository questRepository;
    private final PlayerRepository playerRepository;
    
    @Autowired
    public QuestService( QuestRepository questRepository, PlayerRepository playerRepository )
    {
        this.questRepository = questRepository;
        this.playerRepository = playerRepository;
    }
    
    public QuestEntity getQuestById( long id ) throws Exception
    {
        QuestEntity questEntity = questRepository.getQuestById( id );
        if ( questEntity == null ) 
        {
            throw new Exception( "Quest " + id + " does not exist" );
        }
        
       return questEntity;
    }
    
    public List<QuestEntity> getAll() {
        return questRepository.findAll();
    }
    
    public void createQuest( String name, String latitude, String longitude, String description )
    {
        double tmp_long, tmp_lat;
        tmp_lat = Double.parseDouble( latitude );
        tmp_long = Double.parseDouble( longitude );
        long x = 10;
        QuestEntity questEntity = new QuestEntity();
        questEntity.setLatitude(tmp_lat);
        questEntity.setLongitude(tmp_long);
        questEntity.setName(name);
        questEntity.setDescription(description);
        questRepository.saveAndFlush( questEntity );
        
    }
    
    public void deleteQuest( String id )
    {
        long tmp_id;
        tmp_id = Long.parseLong( id );
        questRepository.deleteQuest( tmp_id );
    }
    
    public List<QuestEntity> getNearbyQuest( String name ) throws Exception
    {
        List<QuestEntity> questsEntity  = questRepository.findAll();
        PlayerEntity currentPlayer = playerRepository.findPlayerByName(name);
        List<QuestEntity> questsEntity2 = new ArrayList<>();
        double radius = 0.001;
        double center_x = currentPlayer.getLatitude();
        double center_y = currentPlayer.getLongitude();
        
        for ( int i = 0; i < questsEntity.size(); i++ )
        {
            if (PlayerService.isInsideOfRadius(center_x, center_y, questsEntity.get(i).getLatitude(), questsEntity.get(i).getLongitude(), radius)) {
                questsEntity2.add(questsEntity.get(i));
            }
        }
        
        if ( questsEntity2 == null ) 
        {
            throw new Exception( "There is no Quest near to the Player: " + name );
        }
        
        return questsEntity2;
    }
}
