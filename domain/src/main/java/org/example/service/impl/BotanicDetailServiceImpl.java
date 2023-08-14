package org.example.service.impl;

import org.example.entity.BotanicDetail;
import org.example.service.BotanicDetailService;
import org.example.spi.port.BotanicDetailRepository;

public class BotanicDetailServiceImpl implements BotanicDetailService {

    private BotanicDetailRepository botanicDetailRepository;

    public BotanicDetailServiceImpl(BotanicDetailRepository botanicDetailRepository) {
        this.botanicDetailRepository = botanicDetailRepository;
    }

    @Override
    public BotanicDetail create(String name) {
        BotanicDetail botanicDetail = new BotanicDetail(name);
        return botanicDetailRepository.save(botanicDetail);
    }

    @Override
    public BotanicDetail update(String name,int id) {
        BotanicDetail botanicDetail = botanicDetailRepository.findById(id);
        botanicDetail.setDetail(name);
        return botanicDetailRepository.save(botanicDetail);
    }

    @Override
    public boolean delete(int id) {
        return botanicDetailRepository.delete(id);
    }

    @Override
    public BotanicDetail findById(int id) {
        return botanicDetailRepository.findById(id);
    }
}
