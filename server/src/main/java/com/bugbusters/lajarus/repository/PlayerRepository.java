package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

/*
    An interface that acces directly to the Database via the entities wit hthe
    support of spring framewokr. The methods of this class are communicating
    with the services and the databases.
*/

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    public PlayerEntity findPlayerById(long id);
    
    public PlayerEntity findPlayerByName(@Param("name") String name);
    
     /*
        The @Query Annotation is from spring framework witch help us to acces
        directly to the database via SQL Query. The mathematical type is calculate
        and returning a list of quests that are near to the player. The informations
        are returning to the Service methods.
        
        The @Param Annotation "telling us that the field with @fieldName(parameter of
        @Param Annotation) is going to be descriped with a variable @fieldName 
        (parameter of the method)
    */
    
    @Query("SELECT p FROM PlayerEntity p"
            + " WHERE ( 6371 * acos( cos( radians( :latitude ) )"
            + " * cos( radians( p.latitude ) )"
            + " * cos( radians( p.longitude ) - radians( :longitude ) )"
            + " + sin( radians( :latitude ) )"
            + " * sin( radians( p.latitude ) ) ) ) <= :distance")
    public List<PlayerEntity> findNearByPlayers(
            @Param("latitude") double latatitude,
            @Param("longitude") double longitude,
            @Param("distance") double radius);
    
    /*
        @Transactional Annotation is from spring framework that give us rights
        to the Database.
        @Modidying Annotation is from spring framweork that give us rights
        to modify th field of the database.
        In this Query we delete a quest with a speicific id.
    */
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( "UPDATE PlayerEntity p SET p.latitude =:latitude, p.longitude =:longitude WHERE p.name =:name" )
    public void updatePlayerLocation( @Param("name") String name,@Param("latitude") double latitude,@Param("longitude") double longitude );
    
}
