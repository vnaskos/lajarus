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
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;

    }
    
    /**
     * Get statistics of a player (Level, XP, Attack, ...)
     * 
     * @param id player id
     * @return stats
     * @throws Exception 
     */
    public StatsEntity getStatsByPlayerId(Long id) throws Exception {
        StatsEntity statsEntity = statsRepository.findByPlayerId(id);
        if (statsEntity == null) {
            throw new Exception("Empty Stats values");
        }

        return statsEntity;
    }

    public List<StatsEntity> getAll() {
        return statsRepository.findAll();
    }

    public void deleteStats(long id) {
        statsRepository.deleteById(id);
    }

    public void saveStats(StatsEntity statsEntity) {
        statsRepository.saveAndFlush(statsEntity);
    }

}
