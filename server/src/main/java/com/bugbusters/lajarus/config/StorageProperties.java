package com.bugbusters.lajarus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * File upload configuration
 * 
 * @author alexavge
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "resources";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
