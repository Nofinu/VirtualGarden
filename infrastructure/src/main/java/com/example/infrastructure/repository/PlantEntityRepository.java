package com.example.infrastructure.repository;

import com.example.infrastructure.entity.PlantEntity;
import com.example.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlantEntityRepository extends CrudRepository<PlantEntity,Integer> {
    List<PlantEntity> findByUser (UserEntity userEntity);
}
