package com.example.demo.service.mapper;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.DonationCenter;
import com.example.demo.entity.Donor;
import com.example.demo.service.CenterService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    @Autowired
    DonorService donorService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    CenterService centerService;

    public Appointment toAppointment(AppointmentCreateDTO dto) {
        Appointment appointment = new Appointment();

        Donor donor = donorService.findById(dto.donorId);
        appointment.setDonor(donor);

        Doctor doctor = doctorService.findById(dto.doctorId);
        appointment.setDoctor(doctor);

        DonationCenter donationCenter = centerService.findById(dto.centerId);
        appointment.setCenter(donationCenter);

        appointment.setConfirmed(Boolean.FALSE);
        appointment.setDate(dto.date);

        return appointment;
    }
}
