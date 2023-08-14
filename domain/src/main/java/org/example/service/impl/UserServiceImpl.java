package org.example.service.impl;

import org.example.entity.Plant;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.spi.port.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(String username, String password) {
        User user = new User(username,password);
        return userRepository.save(user);
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
