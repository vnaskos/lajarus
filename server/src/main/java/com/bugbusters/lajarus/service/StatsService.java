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

    public StatsEntity getStatsById(long id) throws Exception {
        StatsEntity statsEntity = statsRepository.findById(id);
        if (statsEntity == null) {
            throw new Exception("Empty Stats values");
        }

        return statsEntity;
    }

    public List<StatsEntity> getAll() {
        return statsRepository.findAll();
    }

    public void createStats(int health, int power, int defence, int speed) {
        StatsEntity statsEntity = new StatsEntity();
        statsEntity.setPower(power);
        statsEntity.setDefence(defence);
        statsEntity.setHealth(health);
        statsEntity.setSpeed(speed);
        statsRepository.saveAndFlush(statsEntity);
    }

    public void deleteStats(long id) {
        statsRepository.deleteById(id);
    }

    public StatsEntity getStatsByType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createStats(StatsEntity statsEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
