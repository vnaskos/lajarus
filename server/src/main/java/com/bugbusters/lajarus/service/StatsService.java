package com.bugbusters.lajarus.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bugbusters.lajarus.entity.StatsEntity;
import com.bugbusters.lajarus.repository.StatsRepository;


@Service
public class StatsService {
    
    private final StatsRepository statsRepository;
   
    
    @Autowired
    public StatsService( StatsRepository statsRepository )
    {
        this.statsRepository = statsRepository;
        
    }
    
    public StatsEntity getStatsById( long id ) throws Exception
    {
        StatsEntity statsEntity = statsRepository.getstatsById( id );
        if ( statsEntity == null ) 
        {
            throw new Exception( "Empty Stats values" );
        }
        
       return statsEntity;
    }
    
    public List<StatsEntity> getAll() {
        return statsRepository.findAll();
    }
    
    public void createStats( String health, String power, String defence, String speed ) 
    {
        
        StatsEntity statsEntity = new StatsEntity();
        statsEntity.setPower(power);
        statsEntity.setDefence(defence);
        statsEntity.setHealth(health);
        statsEntity.setSpeed(speed);
        statsRepository.saveAndFlush( statsEntity );
        
    }
    
    public void deleteStats( String id )
    {
        long tmp_id;
        tmp_id = Long.parseLong( id );
        statsRepository.deleteStats( tmp_id );
    }
    
    
}