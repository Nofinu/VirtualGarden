package org.example.spi.port;

import org.example.entity.User;

public interface UserRepository {
    User save (User user);
    User findById(int id);
}
