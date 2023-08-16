package com.example.infrastructure.repository;

import com.example.infrastructure.entity.BotanicDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotanicDetailsEntityRepository extends CrudRepository<BotanicDetailEntity,Integer> {

}
