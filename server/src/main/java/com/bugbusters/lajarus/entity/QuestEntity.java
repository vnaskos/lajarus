package com.bugbusters.lajarus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quest")
public class QuestEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "latitude", nullable = false)
    private double latitude;
    
    @Column(name = "longitude", nullable = false)
    private double longitude;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "bookOfQuests", fetch = FetchType.LAZY)
    private Set<PlayerEntity> players;
    
    public QuestEntity() {
        
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the players
     */
    public Set<PlayerEntity> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(Set<PlayerEntity> players) {
        this.players = players;
    }
    
    
}
