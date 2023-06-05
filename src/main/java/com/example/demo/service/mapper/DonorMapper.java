package com.example.demo.service.mapper;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.entity.Donor;
import com.example.demo.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {

    public DonorDTO toDTO(Donor donor) {
        DonorDTO dto = new DonorDTO();
        dto.id = donor.getId();
        dto.email = donor.getEmail();
        dto.firstName = donor.getFirstName();
        dto.lastName = donor.getLastName();
        dto.county = donor.getCounty();
        dto.age = donor.getAge();
        dto.phoneNumber = donor.getPhoneNumber();
        dto.bloodType = donor.getBloodType();
        dto.role = donor.getRole();
        dto.emailNotification = donor.getEmailNotification();
        dto.smsNotification = donor.getSmsNotification();
        return dto;
    }

    public Donor toDonor(DonorCreateDTO dto) {
        Donor donor = new Donor();
        donor.setEmail(dto.email);
        donor.setPassword(dto.password);
        donor.setFirstName(dto.firstName);
        donor.setLastName(dto.lastName);
        donor.setAge(dto.age);
        donor.setPhoneNumber(dto.phoneNumber);
        donor.setCounty(dto.county);
        donor.setBloodType(dto.bloodType);
        donor.setRole(UserRole.Role.donor);
        donor.setEmailNotification(dto.emailNotification);
        donor.setSmsNotification(dto.smsNotification);
        return donor;
    }
}
