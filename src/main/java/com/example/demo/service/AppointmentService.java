package com.example.demo.service;


import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment addAppointment(AppointmentCreateDTO dto);
    List<Appointment> findAllByDonorId(Integer donorId);
    void deleteAppointment(Integer id);
    List<Appointment> findAllByCenterId(Integer centerId);
    Appointment confirmAppointment(Integer id, Integer doctorId);
}
