package org.example;

import org.example.config.AdapterConfig;
import org.example.config.AppInitializer;
import org.example.config.InfrastructureConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        SpringApplication infra = new SpringApplication(InfrastructureConfig.class);
        infra.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
        ConfigurableApplicationContext infraContext = infra.run();

        SpringApplication apiRest = new SpringApplication(AdapterConfig.class);
        apiRest.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        apiRest.addInitializers(new AppInitializer(infraContext));
        ConfigurableApplicationContext apiRestContext = apiRest.run();
    }
}