package com.bugbusters.lajarus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class StatsEntity {
    
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "level", nullable = false)
    private int level;
    
    @Column(name = "health", nullable = false)
    private int health;
    
    @Column(name = "power", nullable = false)
    private int power;
    
    @Column(name = "defence", nullable = false)
    private int defence;
    
    @Column(name = "speed", nullable = false)
    private int speed;
    
    public StatsEntity() {
        
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
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return the defense
     */
    public int getDefence() {
        return defence;
    }

    /**
     * @param defence the defense to set
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    

   
    
}

