package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.QuestEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface QuestRepository extends JpaRepository<QuestEntity, Long> {

    public QuestEntity getQuestById( @Param("id") long id );
    
    @Query("SELECT q FROM QuestEntity q"
            + " WHERE 6371 * acos(cos(radians(:latitude))"
            + " * cos(radians(q.latitude))"
            + " * cos(radians(q.longitude)"
            + " - radians(:longitude))"
            + " + sin(radians(:latitude))"
            + " * sin(radians(q.latitude))) <= :distance")
    public List<QuestEntity> findNearByQuests(
            @Param("latitude") double latatitude,
            @Param("longitude") double longitude,
            @Param("distance") double radius);
    
    @Transactional
    @Modifying
    @Query( "DELETE from QuestEntity q WHERE q.id = :id" )
    public void deleteQuest( @Param("id") long id );
    
    
}
