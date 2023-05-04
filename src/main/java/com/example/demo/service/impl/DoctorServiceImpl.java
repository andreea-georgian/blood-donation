package com.example.demo.service.impl;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.DoctorService;
import com.example.demo.service.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public DoctorDTO registerDoctor(DoctorCreateDTO dto) {
        if(doctorRepository.findByEmail(dto.email).isEmpty()) {
            Doctor savedDoctor = doctorRepository.save(doctorMapper.toDoctor(dto));
            return doctorMapper.toDTO(savedDoctor);
        }
        else
            return null;
    }

    @Override
    public List<DoctorDTO> allDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        for (Doctor d : doctors) {
            doctorsDTO.add(doctorMapper.toDTO(d));
        }
        return doctorsDTO;
    }

    @Override
    public void deleteDoctor(Integer id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent())
            doctorRepository.delete(doctor.get());
    }

    @Override
    public DoctorDTO updateDoctor(Integer id, DoctorCreateDTO dto) {
        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setId(id);
        updatedDoctor.setEmail(dto.email);
        updatedDoctor.setPassword(dto.password);
        updatedDoctor.setFirstName(dto.firstName);
        updatedDoctor.setLastName(dto.lastName);
        updatedDoctor.setDonationCenter(dto.donationCenter);
        updatedDoctor.setRole(UserRole.Role.doctor);
        Doctor savedDoctor = doctorRepository.save(updatedDoctor);
        return doctorMapper.toDTO(savedDoctor);
    }
}
