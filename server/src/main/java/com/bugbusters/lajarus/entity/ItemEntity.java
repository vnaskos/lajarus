package com.bugbusters.lajarus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {

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
     * @param description the name to set
     */
    public void setdescription(String description) {
        this.description = description;
    }
    /**
     * @return the description
     */
    public String getdescription() {
        return description;
    }
     /**
     * @param type the name to set
     */
    public void settype(String type) {
        this.type = type;
    }
    /**
     * @return the type
     */
    public String gettype() {
        return type;
    }
     /**
     * @return the value
     */
    public long getvalue() {
        return value;
    }

    /**
     * @param value the id to set
     */
    public void setvalue(long value) {
        this.value =value;
    }
     /**
     * @return the price
     */
    public long getprice() {
        return price;
    }

    /**
     * @param price the id to set
     */
    public void setprice(long price) {
        this.price = price;
    }


    
}
