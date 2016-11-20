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
    private long level;
    
    @Column(name = "health", nullable = false)
    private String health;
    
    @Column(name = "power", nullable = false)
    private double power;
    
    @Column(name = "defence", nullable = false)
    private double defence;
    
    @Column(name = "speed", nullable = false)
    private String speed;
    
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
    public long getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(long level) {
        this.level = level;
    }

    /**
     * @return the health
     */
    public String getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(String health) {
        this.health = health;
    }

    /**
     * @return the power
     */
    public double getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * @return the defense
     */
    public double getDefence() {
        return defence;
    }

    /**
     * @param defence the defense to set
     */
    public void setDefence(double defence) {
        this.defence = defence;
    }

    /**
     * @return the speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setDefence(String defence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPower(String power) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

