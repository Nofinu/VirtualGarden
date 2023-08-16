package com.example.infrastructure.repository;

import com.example.infrastructure.entity.PlantEntity;
import com.example.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantEntityRepository extends CrudRepository<PlantEntity,Integer> {
    List<PlantEntity> findByUser (UserEntity userEntity);
}
