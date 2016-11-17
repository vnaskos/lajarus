package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.QuestEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface QuestRepository extends JpaRepository<QuestEntity, Long> {

    @Query( "SELECT q FROM QuestEntity q WHERE q.id = :id" )
    public QuestEntity getQuestById( @Param("id") long id );
    
    @Transactional
    @Modifying
    @Query( "DELETE from QuestEntity q WHERE q.id = :id" )
    public void deleteQuest( @Param("id") long id );
    
    
}
