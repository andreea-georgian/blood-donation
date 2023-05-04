package com.example.demo.service.impl;

import com.example.demo.entity.DonationCenter;
import com.example.demo.repository.CenterRepository;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;
    @Override
    public List<DonationCenter> findAllByCounty(String county) {
        Optional<List<DonationCenter>> donationCenters = centerRepository.findAllByCounty(county);
        if (donationCenters.isPresent())
            return centerRepository.findAllByCounty(county).get();
        else
            return null;
    }

    @Override
    public DonationCenter findById(int id) {
        Optional<DonationCenter> donationCenter = centerRepository.findById(id);
        if(donationCenter.isPresent())
            return donationCenter.get();
        else return null;
    }


}
