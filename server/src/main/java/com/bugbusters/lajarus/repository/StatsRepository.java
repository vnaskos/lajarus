package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.StatsEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    @Transactional
    public void deleteById(long id);

    public StatsEntity findById(long id);
    
    /**
     *
     * @param player
     * @return
     */
    @Query("SELECT s FROM StatsEntity s WHERE s.player.id = :player")
    public StatsEntity findByPlayerId(@Param("player") Long player);

    
}