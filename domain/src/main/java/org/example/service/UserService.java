package org.example.service;

import org.example.entity.Plant;
import org.example.entity.User;

import java.util.List;

public interface UserService {
    User create (String username, String password);
    User findById(int userId);
    User findByUsername(String username);

}
