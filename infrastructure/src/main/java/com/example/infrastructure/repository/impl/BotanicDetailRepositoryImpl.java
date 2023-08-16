package com.example.infrastructure.repository.impl;

import com.example.infrastructure.Exception.NotFoundException;
import com.example.infrastructure.entity.BotanicDetailEntity;
import com.example.infrastructure.repository.BotanicDetailsEntityRepository;
import org.example.entity.BotanicDetail;
import org.example.spi.port.BotanicDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BotanicDetailRepositoryImpl implements BotanicDetailRepository {

    private final BotanicDetailsEntityRepository botanicDetailsEntityRepository;

    private final ModelMapper modelMapper;

    public BotanicDetailRepositoryImpl(BotanicDetailsEntityRepository botanicDetailsEntityRepository) {
        this.botanicDetailsEntityRepository = botanicDetailsEntityRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public BotanicDetail save(BotanicDetail botanicDetail) {
        BotanicDetailEntity botanicDetailEntity = modelMapper.map(botanicDetail,BotanicDetailEntity.class);
        return modelMapper.map(botanicDetailsEntityRepository.save(botanicDetailEntity),BotanicDetail.class);
    }

    @Override
    public boolean delete(int id) {
        BotanicDetailEntity botanicDetailEntity = modelMapper.map(findById(id),BotanicDetailEntity.class);
        botanicDetailsEntityRepository.delete(botanicDetailEntity);
        return true;
    }

    @Override
    public BotanicDetail findById(int idBD) {
        Optional<BotanicDetailEntity> botanicDetailEntity = botanicDetailsEntityRepository.findById(idBD);
        if(botanicDetailEntity.isPresent()){
            return modelMapper.map(botanicDetailEntity.get(),BotanicDetail.class);
        }
        throw new NotFoundException();
    }

    @Override
    public List<BotanicDetail> findAll() {
        List<BotanicDetail> botanicDetails = new ArrayList<>();
        botanicDetailsEntityRepository.findAll().forEach(bd ->{
            botanicDetails.add(modelMapper.map(bd,BotanicDetail.class));
        });
        return botanicDetails;
    }
}
