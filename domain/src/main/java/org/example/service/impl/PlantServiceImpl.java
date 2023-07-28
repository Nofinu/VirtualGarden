package org.example.service.impl;

import org.example.Exception.AlreadyUseWateringTodayException;
import org.example.Exception.WrongUserException;
import org.example.entity.BotanicDetail;
import org.example.entity.Plant;
import org.example.entity.User;
import org.example.service.PlantService;
import org.example.spi.port.BotanicDetailRepository;
import org.example.spi.port.PlantRepository;
import org.example.spi.port.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final BotanicDetailRepository botanicDetailRepository;

    public PlantServiceImpl(PlantRepository plantRepository,UserRepository userRepository,BotanicDetailRepository botanicDetailRepository) {
        this.plantRepository = plantRepository;
        this.userRepository= userRepository;
        this.botanicDetailRepository = botanicDetailRepository;
    }

    @Override
    public Plant create(String name, String description, String imageUrl, List<Integer> botanicDetails, int userId) {
        User user = userRepository.findById(userId);
        List<BotanicDetail> botanicDetailsFind = new ArrayList<>();

        botanicDetails.forEach(bd ->{
            botanicDetailsFind.add(botanicDetailRepository.findById(bd));
        });

        Plant plant = new Plant(name,description,imageUrl,botanicDetailsFind,user);
        return plantRepository.save(plant);
    }

    @Override
    public Plant update(int plantId, String name, String description, String imageUrl, List<Integer> botanicDetails, int growProgression, int plantLevel, int userId) {
        User user = userRepository.findById(userId);
        Plant plant = plantRepository.findById(plantId);
        if(user.equals(plant.getUser())){
            List<BotanicDetail> botanicDetailsFind = new ArrayList<>();
            botanicDetails.forEach(bd ->{
                botanicDetailsFind.add(botanicDetailRepository.findById(bd));
            });
            plant.setName(name);
            plant.setDescription(description);
            plant.setImageUrl(imageUrl);
            plant.setBotanicDetails(botanicDetailsFind);
            plant.setGrowProgression(growProgression);
            plant.setPlantLevel(plantLevel);

            return plantRepository.save(plant);
        }

        throw new WrongUserException();
    }

    @Override
    public boolean delete(int plantId) {
        return plantRepository.delete(plantId);
    }

    @Override
    public Plant watering(int plantId) {
        Plant plant = plantRepository.findById(plantId);

        if(plant.getLastWatering() == null || plant.getLastWatering().isBefore(LocalDate.now())){
            int grow =(int) Math.floor(Math.random() * ( 11 - 1 ));
            plant.setGrowProgression(plant.getGrowProgression()+grow);
            plant.setLastWatering(LocalDate.now());
            if(plant.getGrowProgression()>=100){
                plant.setGrowProgression(plant.getGrowProgression()-100);
                plant.setPlantLevel(plant.getPlantLevel()+1);
            }
            return plantRepository.save(plant);
        }
        throw new AlreadyUseWateringTodayException();
    }

    @Override
    public Plant findById(int plantId) {
        return plantRepository.findById(plantId);
    }

    @Override
    public List<Plant> findByUserId(int userId) {
        User user = userRepository.findById(userId);
        return plantRepository.findByUserId(user);
    }

    @Override
    public List<Plant> findAll() {
        return plantRepository.findAll();
    }
}
