package org.example.spi.port;

import org.example.entity.Plant;
import org.example.entity.User;

import java.util.List;

public interface PlantRepository {
    Plant save (Plant plant);
    boolean delete (int plantId);
    Plant findById(int plantId);
    List<Plant> findByUserId (User user);
    List<Plant> findAll ();
}
