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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    AppointmentMapper appointmentMapper;
    @Autowired
    DoctorService doctorService;

    @Override
    public Appointment addAppointment(AppointmentCreateDTO dto) {
        List<Doctor> doctorList = doctorService.findByCenterId(dto.centerId);
        Doctor appointmentDoctor = doctorList.get(0);
        int minAppointments = findAllByDoctorId(appointmentDoctor.getId()).size();
        doctorList.remove(appointmentDoctor);

        for(Doctor d : doctorList) {
            int count = findAllByDoctorId(d.getId()).size();
            if (count < minAppointments) {
                appointmentDoctor = d;
                minAppointments = count;
            }
        }

        Appointment newAppointment = appointmentMapper.toAppointment(dto);
        newAppointment.setDoctor(appointmentDoctor);

        return appointmentRepository.save(newAppointment);
    }

    @Override
    public List<Appointment> findAllByDonorId(Integer donorId) {
        List<Appointment> appointments = appointmentRepository.findAllByDonorId(donorId);
        return appointments;
    }

    @Override
    public Boolean deleteAppointment(Integer id) {
        Optional<Appointment> appointmentToDelete = appointmentRepository.findById(id);
        Date appointmentDate = appointmentToDelete.get().getDate();
        if (appointmentToDelete.isPresent()) {
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (appointmentDate.compareTo(currentDate) > 0) {
                appointmentRepository.delete(appointmentToDelete.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteAppointmentsByDoctorId(Integer doctorId) {
        List<Appointment> appointmentsToDelete = appointmentRepository.findAllByDoctorId(doctorId);
        for(Appointment a : appointmentsToDelete) {
            deleteAppointment(a.getId());
        }
    }

    @Override
    public List<Appointment> findAllByCenterId(Integer centerId) {
        List<Appointment> appointmentsByCenter = appointmentRepository.findAllByCenterId(centerId);
        return appointmentsByCenter;
    }

    @Override
    public List<Appointment> findAllByDoctorId(Integer doctorId) {
        List<Appointment> appointmentsByDoctor = appointmentRepository.findAllByDoctorId(doctorId);
        return appointmentsByDoctor;
    }

    @Override
    public Appointment confirmAppointment(Integer id) {
        Optional<Appointment> confirmedAppointment = appointmentRepository.findById(id);
        if (confirmedAppointment.isPresent()) {
            confirmedAppointment.get().setConfirmed(Boolean.TRUE);
            return appointmentRepository.save(confirmedAppointment.get());
        }
        else return null;
    }

    @Override
    public List<Appointment> findAllByDoctorAndDate(Integer doctorId, Date date) {
        List<Appointment> appointments = appointmentRepository.findAllByDoctorIdAndDate(doctorId, date);
        return appointments;
    }

    @Override
    public List<Appointment> findAllByDate(java.sql.Date date) {
        return appointmentRepository.findAllByDate(date);
    }

}
