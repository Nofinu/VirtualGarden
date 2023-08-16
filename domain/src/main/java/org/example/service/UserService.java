package org.example.service;

import org.example.entity.Plant;
import org.example.entity.User;

import java.util.List;

public interface UserService {
    User save (String username, String password);
    User findByUsername (String username);
}
