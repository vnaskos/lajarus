package com.bugbusters.lajarus;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJSONDoc
public class LajarusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LajarusApplication.class, args);
    }
}
