package com.example.demo.service;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.entity.Donor;

import java.util.List;

public interface DonorService {
    DonorDTO updateDonor(Integer id, DonorCreateDTO dto);
    void deleteDonor(Integer id);
    Donor findById(Integer id);
    List<Donor> findAllByCounty(String county);
}
