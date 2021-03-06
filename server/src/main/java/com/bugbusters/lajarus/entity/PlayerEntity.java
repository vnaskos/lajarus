package com.bugbusters.lajarus.entity;

import com.bugbusters.lajarus.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * Database mapping of the player table
 */
@Entity
@Table(name = "player")
public class PlayerEntity implements Serializable {

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
    
    @Column(name = "online", nullable = false)
    private boolean online;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_quest",
            joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "quest_id", referencedColumnName = "id")}
    )
    private Set<QuestEntity> bookOfQuests;
    
    @ManyToMany(fetch = FetchType.EAGER)
    
    @JoinTable(
            name = "player_item",
            joinColumns = {@JoinColumn(name = "player_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    private Set<ItemEntity> inventory;
    
    @ApiObjectField(description = "Stats", processtemplate = false)
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private StatsEntity stats;
    
    public PlayerEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean active) {
        this.online = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ItemEntity> getInventory() {
        return inventory;
    }

    public void setInventory(Set<ItemEntity> inventory) {
        this.inventory = inventory;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public void setStats(StatsEntity stats) {
        this.stats = stats;
    }
}
