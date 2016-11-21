package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.StatsEntity;
import javax.transaction.Transactional;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    @Transactional
    public void deleteById(long id);

    public StatsEntity findById(long id);

    
}