package com.example.demo.service;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;

public interface DonorService {
    DonorDTO updateDonor(Integer id, DonorCreateDTO dto);
}
