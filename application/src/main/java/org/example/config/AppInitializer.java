package org.example.config;

import com.example.infrastructure.repository.BotanicDetailsEntityRepository;
import com.example.infrastructure.repository.PlantEntityRepository;
import com.example.infrastructure.repository.impl.BotanicDetailRepositoryImpl;
import com.example.infrastructure.repository.impl.PlantRepositoryImpl;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class AppInitializer implements ApplicationContextInitializer {
    private ConfigurableApplicationContext applicationContextInfrastructure;

    public AppInitializer(ConfigurableApplicationContext applicationContextInfrastructure) {
        this.applicationContextInfrastructure = applicationContextInfrastructure;
    }
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableListableBeanFactory registry = applicationContext.getBeanFactory();
        registry.registerSingleton("BotanicDetailRepository",new BotanicDetailRepositoryImpl(applicationContextInfrastructure.getBean(BotanicDetailsEntityRepository.class)));
        registry.registerSingleton("PlatRepositoryImpl",new PlantRepositoryImpl(applicationContextInfrastructure.getBean(PlantEntityRepository.class)));
        registry.registerSingleton("UserRepository",new PlantRepositoryImpl(applicationContextInfrastructure.getBean(PlantEntityRepository.class)));

    }
}
