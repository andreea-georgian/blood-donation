package com.example.demo.service.impl;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DonationCenter;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.CenterService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.UserService;
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
    @Autowired
    CenterService centerService;
    @Autowired
    UserService userService;

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
        User user = userService.findById(id);
        updatedDoctor.setPassword(user.getPassword());
        updatedDoctor.setFirstName(dto.firstName);
        updatedDoctor.setLastName(dto.lastName);
        DonationCenter donationCenter = centerService.findById(dto.donationCenterId);
        updatedDoctor.setDonationCenter(donationCenter);
        updatedDoctor.setRole(UserRole.Role.doctor);
        Doctor savedDoctor = doctorRepository.save(updatedDoctor);
        return doctorMapper.toDTO(savedDoctor);
    }

//    @Override
//    public Doctor findById(Integer id) {
//        Optional<Doctor> doctor = doctorRepository.findById(id);
//        if (doctor.isPresent())
//            return doctor.get();
//        else
//            return null;
//    }

    @Override
    public List<Doctor> findByCenterId(Integer centerId) {
        DonationCenter center = centerService.findById(centerId);
        List<Doctor> doctorList = doctorRepository.findByDonationCenter(center);
        return doctorList;
    }
}
