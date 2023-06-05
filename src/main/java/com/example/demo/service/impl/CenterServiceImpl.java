package com.example.demo.service.impl;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.DonationCenter;
import com.example.demo.repository.CenterRepository;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    CenterRepository centerRepository;
    @Override
    public List<DonationCenter> findAllByCounty(String county) {
        Optional<List<DonationCenter>> donationCenters = centerRepository.findAllByCounty(county);
        if (donationCenters.isPresent())
            return donationCenters.get();
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

    @Override
    public List<LocalDate> validDates(int id, List<Appointment> appointments) {
        LocalDate startDate = LocalDate.now();
        int centerCapacity = findById(id).getCapacity();

        Map<LocalDate, Integer> validDates = new HashMap<>();
        for (int i = 0; i < 31; i++) {
            LocalDate currentDay = startDate.plusDays(i);
            validDates.put(currentDay, centerCapacity);
        }

        for (Appointment a:appointments) {
            LocalDate currentDate = a.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (currentDate.compareTo(startDate) > 0) {
                int currentCapacity = validDates.get(currentDate);
                if (currentCapacity == 1)
                    validDates.remove(currentDate);
                else {
                    validDates.put(currentDate, currentCapacity - 1);
                }
            }
        }

        List<LocalDate> returnDates = new ArrayList<>(validDates.keySet());

        return returnDates;
    }
}
