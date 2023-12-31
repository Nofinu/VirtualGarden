package org.example.config;

import com.example.infrastructure.repository.UserEntityRepository;
import com.example.infrastructure.repository.impl.BotanicDetailRepositoryImpl;
import com.example.infrastructure.repository.impl.PlantRepositoryImpl;
import com.example.infrastructure.repository.impl.UserRepositoryImpl;
import org.example.service.impl.BotanicDetailServiceImpl;
import org.example.service.impl.PlantServiceImpl;
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
        registry.registerSingleton("BotanicDetailService",new BotanicDetailServiceImpl(applicationContextInfrastructure.getBean(BotanicDetailRepositoryImpl.class)));
        registry.registerSingleton("UserRepository",new UserRepositoryImpl(applicationContextInfrastructure.getBean(UserEntityRepository.class)));
        registry.registerSingleton("PlantService",new PlantServiceImpl(applicationContextInfrastructure.getBean(PlantRepositoryImpl.class),applicationContextInfrastructure.getBean(UserRepositoryImpl.class),applicationContextInfrastructure.getBean(BotanicDetailRepositoryImpl.class)));
    }
}
