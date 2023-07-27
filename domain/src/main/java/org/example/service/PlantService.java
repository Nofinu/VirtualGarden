package org.example.service;

import org.example.entity.Plant;
import org.example.entity.User;

import java.util.List;

public interface PlantService {
    Plant create (String name, String description, String imageUrl, List<Integer> botanicDetails, int userId);
    Plant update (int plantId, String name, String description, String imageUrl, List<Integer> botanicDetails, int growProgression, int plantLevel, int userId);
    boolean delete (int plantId);
    Plant watering (int plantId);
    Plant findById(int plantId);
    List<Plant> findByUserId(int userId);
    List<Plant> findAll();
}
