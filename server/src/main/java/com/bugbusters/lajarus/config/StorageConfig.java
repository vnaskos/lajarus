package com.bugbusters.lajarus.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Vasilis Naskos <vnaskos.com>
 */
@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {
    
}
