package com.bugbusters.lajarus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bugbusters.lajarus.entity.PlayerEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

    public PlayerEntity findPlayerById(long id);
    
    public PlayerEntity findPlayerByName(@Param("name") String name);
    
    @Query("SELECT p FROM PlayerEntity p"
            + " WHERE 6371 * acos(cos(:latitude)"
            + " * cos(p.latitude)"
            + " * cos(p.longitude - :longitude)"
            + " + sin(:latitude)"
            + " * sin(p.latitude)) <= :distance")
    public List<PlayerEntity> findNearByPlayers(
            @Param("latitude") double latatitude,
            @Param("longitude") double longitude,
            @Param("distance") double radius);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query( "UPDATE PlayerEntity p SET p.latitude =:latitude, p.longitude =:longitude WHERE p.name =:name" )
    public void updatePlayerLocation( @Param("name") String name,@Param("latitude") double latitude,@Param("longitude") double longitude );
    
}
