package org.example.service;

import org.example.entity.BotanicDetail;
import org.example.entity.User;

public interface BotanicDetailService {
    BotanicDetail create (String name);
    BotanicDetail update (String name, int id);
    boolean delete (int id);
    BotanicDetail findById(int Id);

}
