package com.example.demo.service.impl;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentMapper appointmentMapper;
    @Autowired
    DoctorService doctorService;

    public Appointment addAppointment(AppointmentCreateDTO dto) {
        Appointment newAppointment = appointmentRepository.save(appointmentMapper.toAppointment(dto));
        return newAppointment;
    }

    @Override
    public List<Appointment> findAllByDonorId(Integer donorId) {
        List<Appointment> appointments = appointmentRepository.findAllByDonorId(donorId);
        return appointments;
    }

    @Override
    public void deleteAppointment(Integer id) {
        Optional<Appointment> appointmentToDelete = appointmentRepository.findById(id);
        if (appointmentToDelete.isPresent())
            appointmentRepository.delete(appointmentToDelete.get());
    }

    @Override
    public List<Appointment> findAllByCenterId(Integer centerId) {
        List<Appointment> appointmentsByCenter = appointmentRepository.findAllByCenterId(centerId);
        return appointmentsByCenter;
    }

    @Override
    public Appointment confirmAppointment(Integer id, Integer doctorId) {
        Doctor doctor = doctorService.findById(doctorId);
        Optional<Appointment> confirmedAppointment = appointmentRepository.findById(id);
        if (confirmedAppointment.isPresent()) {
            confirmedAppointment.get().setConfirmed(Boolean.TRUE);
            confirmedAppointment.get().setDoctor(doctor);
            return appointmentRepository.save(confirmedAppointment.get());
        }
        else return null;
    }
}
