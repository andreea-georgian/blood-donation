package com.example.demo.service.mapper;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DonationCenter;
import com.example.demo.entity.UserRole;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    @Autowired
    CenterService centerService;

    public DoctorDTO toDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.id = doctor.getId();
        dto.email = doctor.getEmail();
        dto.firstName = doctor.getFirstName();
        dto.lastName = doctor.getLastName();
        dto.donationCenterId = doctor.getDonationCenter().getId();
        dto.role = doctor.getRole();
        return dto;
    }

    public Doctor toDoctor(DoctorCreateDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setEmail(dto.email);
        doctor.setPassword(dto.password);
        doctor.setFirstName(dto.firstName);
        doctor.setLastName(dto.lastName);
        DonationCenter donationCenter = centerService.findById(dto.donationCenterId);
        doctor.setDonationCenter(donationCenter);
        doctor.setRole(UserRole.Role.doctor);
        return doctor;
    }
}
