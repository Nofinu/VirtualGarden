package com.example.infrastructure.repository;

import com.example.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
}
