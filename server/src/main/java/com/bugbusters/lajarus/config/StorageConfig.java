package com.bugbusters.lajarus.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Enable "storage" functionalities,
 * used for profile image upload
 * 
 * @author Vasilis Naskos
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {
    
}
