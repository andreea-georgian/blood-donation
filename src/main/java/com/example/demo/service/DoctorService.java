package com.example.demo.service;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorDTO registerDoctor(DoctorCreateDTO dto);
    List<DoctorDTO> allDoctors();
    void deleteDoctor(Integer id);
    DoctorDTO updateDoctor(Integer id, DoctorCreateDTO dto);
    Doctor findById(Integer id);
}
