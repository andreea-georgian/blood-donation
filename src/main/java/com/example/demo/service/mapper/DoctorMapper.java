package com.example.demo.service.mapper;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Donor;
import com.example.demo.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDTO toDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.id = doctor.getId();
        dto.email = doctor.getEmail();
        dto.firstName = doctor.getFirstName();
        dto.lastName = doctor.getLastName();
        dto.donationCenter = doctor.getDonationCenter();
        dto.role = doctor.getRole();
        return dto;
    }

    public Doctor toDoctor(DoctorCreateDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setEmail(dto.email);
        doctor.setPassword(dto.password);
        doctor.setFirstName(dto.firstName);
        doctor.setLastName(dto.lastName);
        doctor.setDonationCenter(dto.donationCenter);
        doctor.setRole(UserRole.Role.doctor);
        return doctor;
    }
}
