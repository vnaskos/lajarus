package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.QuestEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

/**
 * Repository for database communication about quest queries
 * 
 * @author karen
 */
@Repository
public interface QuestRepository extends JpaRepository<QuestEntity, Long> {
    
    /**
     * Get a single quest by id
     * 
     * @param id
     * @return Quest 
     */
    public QuestEntity getQuestById( @Param("id") long id );
    
    /**
     * Get quests near to the requested location
     * 
     * @param latatitude
     * @param longitude
     * @param radius radius from the requested location (in meters)
     * @return List of Quests
     */
    @Query("SELECT q FROM QuestEntity q"
            + " WHERE ( 6371 * acos( cos( radians( :latitude ) )"
            + " * cos( radians( q.latitude ) )"
            + " * cos( radians( q.longitude ) - radians( :longitude ) )"
            + " + sin( radians( :latitude ) )"
            + " * sin( radians( q.latitude ) ) ) ) <= :distance")
    public List<QuestEntity> findNearByQuests(
            @Param("latitude") double latatitude,
            @Param("longitude") double longitude,
            @Param("distance") double radius);
    
    /**
     * Delete quest by id
     * Changes are propagated directly to the database
     * 
     * @param id Quest id
     */
    @Transactional
    @Modifying
    @Query( "DELETE from QuestEntity q WHERE q.id = :id" )
    public void deleteQuest( @Param("id") long id );
    
    /**
     * Get a single quest by name
     * 
     * @param name
     * @return Quest
     */
    @Query("SELECT q FROM QuestEntity q WHERE q.name = :name")
    public QuestEntity isThereQuestByName( @Param("name") String name );
    
}
