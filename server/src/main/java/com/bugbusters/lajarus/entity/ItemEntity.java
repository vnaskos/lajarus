package com.bugbusters.lajarus.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue

    @Column(name = "id", nullable = false)
    private long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "value", nullable = false)
    private long value;
    
    @Column(name = "price", nullable = false)
    private long price;
    
    public ItemEntity() {
        
    }
    
    /**
     * @return the id
     */
    public long getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setID(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
     public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the name to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
     /**
     * @param type the name to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
     /**
     * @return the value
     */
    public long getValue() {
        return value;
    }

    /**
     * @param value the id to set
     */
    public void setValue(long value) {
        this.value =value;
    }
     /**
     * @return the price
     */
    public long getPrice() {
        return price;
    }

    /**
     * @param price the id to set
     */
    public void setPrice(long price) {
        this.price = price;
    }


    
}
