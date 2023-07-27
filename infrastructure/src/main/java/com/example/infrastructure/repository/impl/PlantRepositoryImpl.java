package com.example.infrastructure.repository.impl;

import com.example.infrastructure.Exception.NotFoundException;
import com.example.infrastructure.entity.BotanicDetailEntity;
import com.example.infrastructure.entity.PlantEntity;
import com.example.infrastructure.entity.UserEntity;
import com.example.infrastructure.repository.PlantEntityRepository;
import org.example.entity.BotanicDetail;
import org.example.entity.Plant;
import org.example.entity.User;
import org.example.spi.port.PlantRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantRepositoryImpl implements PlantRepository {

    private final PlantEntityRepository plantEntityRepository;

    private final ModelMapper modelMapper;

    public PlantRepositoryImpl(PlantEntityRepository plantEntityRepository) {
        this.plantEntityRepository = plantEntityRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public Plant save(Plant plant) {
        PlantEntity plantEntity = modelMapper.map(plant, PlantEntity.class);
        plantEntity.setUser(modelMapper.map(plant.getUser(), UserEntity.class));
        List<BotanicDetailEntity> botanicDetailEntities = new ArrayList<>();
        plant.getBotanicDetails().forEach(bd->{
            botanicDetailEntities.add(modelMapper.map(bd, BotanicDetailEntity.class));
        });
        plantEntity.setBotanicDetails(botanicDetailEntities);
        return modelMapper.map(plantEntityRepository.save(plantEntity), Plant.class);
    }

    @Override
    public boolean delete(int plantId) {
        Optional<PlantEntity> plantEntity = plantEntityRepository.findById(plantId);
        if(plantEntity.isPresent()){
            plantEntityRepository.delete(plantEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public Plant findById(int plantId)  {
        Optional<PlantEntity> plantEntity = plantEntityRepository.findById(plantId);
        if(plantEntity.isPresent()){
            Plant plant =modelMapper.map(plantEntity.get(), Plant.class);
            plant.setUser(modelMapper.map(plantEntity.get().getUser(), User.class));
            List<BotanicDetail> botanicDetails = new ArrayList<>();
            plantEntity.get().getBotanicDetails().forEach(bd->{
                botanicDetails.add(modelMapper.map(bd, BotanicDetail.class));
            });
            plant.setBotanicDetails(botanicDetails);
            return plant;
        }
        throw new NotFoundException();
    }

    @Override
    public List<Plant> findByUserId(User user) {
        return convertListPlantEntity(plantEntityRepository.findByUser(modelMapper.map(user, UserEntity.class)));
    }

    @Override
    public List<Plant> findAll() {
        return convertListPlantEntity((List<PlantEntity>) plantEntityRepository.findAll());
    }

    private List<Plant> convertListPlantEntity(List<PlantEntity> plantEntities) {
        List<Plant> plants = new ArrayList<>();
        plantEntities.forEach(p -> {
            Plant plant =modelMapper.map(p, Plant.class);
            plant.setUser(modelMapper.map(p.getUser(), User.class));
            List<BotanicDetail> botanicDetails = new ArrayList<>();
            p.getBotanicDetails().forEach(bd->{
                botanicDetails.add(modelMapper.map(bd, BotanicDetail.class));
            });
            plant.setBotanicDetails(botanicDetails);
            plants.add(plant);
        });
        return plants;
    }
}
