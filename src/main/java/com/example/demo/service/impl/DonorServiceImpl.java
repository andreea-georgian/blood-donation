package com.example.demo.service.impl;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.entity.Donor;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.DonorRepository;
import com.example.demo.service.DonorService;
import com.example.demo.service.mapper.DonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    DonorRepository donorRepository;
    @Autowired
    DonorMapper donorMapper;

    @Override
    public DonorDTO updateDonor(Integer id, DonorCreateDTO dto) {
        Donor updatedDonor = new Donor();
        updatedDonor.setId(id);
        updatedDonor.setEmail(dto.email);
        updatedDonor.setPassword(dto.password);
        updatedDonor.setFirstName(dto.firstName);
        updatedDonor.setLastName(dto.lastName);
        updatedDonor.setAge(dto.age);
        updatedDonor.setCounty(dto.county);
        updatedDonor.setBloodType(dto.bloodType);
        updatedDonor.setRole(UserRole.Role.donor);
        Donor savedDonor = donorRepository.save(updatedDonor);
        return donorMapper.toDTO(savedDonor);
    }
}
