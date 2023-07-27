package org.example.spi.port;

import org.example.entity.BotanicDetail;

import java.util.List;

public interface BotanicDetailRepository {
    BotanicDetail save (BotanicDetail botanicDetail);
    boolean delete (int id);
    BotanicDetail findById(int idBD);
    List<BotanicDetail> findAll ();
}
