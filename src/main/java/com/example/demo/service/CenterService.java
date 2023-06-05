package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.DonationCenter;

import java.time.LocalDate;
import java.util.List;

public interface CenterService {
    List<DonationCenter> findAllByCounty(String county);
    DonationCenter findById(int id);
    List<LocalDate> validDates(int id, List<Appointment> appointments);
}
