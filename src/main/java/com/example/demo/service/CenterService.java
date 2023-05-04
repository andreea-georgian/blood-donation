package com.example.demo.service;

import com.example.demo.entity.DonationCenter;

import java.util.List;

public interface CenterService {
    List<DonationCenter> findAllByCounty(String county);
    DonationCenter findById(int id);
}
