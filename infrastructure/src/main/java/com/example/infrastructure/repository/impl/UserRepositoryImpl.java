package com.example.infrastructure.repository.impl;

import com.example.infrastructure.Exception.NotFoundException;
import com.example.infrastructure.entity.UserEntity;
import com.example.infrastructure.repository.UserEntityRepository;
import org.example.entity.User;
import org.example.spi.port.UserRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    private final ModelMapper modelMapper;

    public UserRepositoryImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        return modelMapper.map(userEntityRepository.save(userEntity), User.class);
    }

    @Override
    public User findById(int id) {
        Optional<UserEntity> userEntity = userEntityRepository.findById(id);
        if(userEntity.isPresent()){
            return modelMapper.map(userEntity.get(), User.class);
        }
        throw new NotFoundException();
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userEntityRepository.findByUsername(username);
        if(userEntity != null){
            return modelMapper.map(userEntity, User.class);
        }
        throw new NotFoundException();
    }
}
