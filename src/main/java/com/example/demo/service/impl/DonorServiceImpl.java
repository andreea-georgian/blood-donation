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

import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    @Autowired
    DonorRepository donorRepository;
    @Autowired
    DonorMapper donorMapper;

    @Override
    public DonorDTO updateDonor(Integer id, DonorCreateDTO dto) {
        Donor updatedDonor = donorMapper.toDonor(dto);
        updatedDonor.setId(id);
        Donor savedDonor = donorRepository.save(updatedDonor);
        return donorMapper.toDTO(savedDonor);
    }

    public void deleteDonor(Integer id) {
        Optional<Donor> donorToDelete = donorRepository.findById(id);
        if (donorToDelete.isPresent())
            donorRepository.delete(donorToDelete.get());
    }

    public Donor findById(Integer id) {
        Optional<Donor> donor = donorRepository.findById(id);
        if (donor.isPresent())
            return donorRepository.findById(id).get();
        else return null;
    }
}
