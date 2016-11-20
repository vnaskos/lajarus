package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.QuestEntity;
import com.bugbusters.lajarus.entity.StatsEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    public void deleteStats(long tmp_id);

    public StatsEntity getstatsById(long id);

    
}