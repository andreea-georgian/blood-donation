package com.example.demo.service;


import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentService {
    Appointment addAppointment(AppointmentCreateDTO dto);
    List<Appointment> findAllByDonorId(Integer donorId);
    List<Appointment> findAllByCenterId(Integer centerId);
    List<Appointment> findAllByDoctorId(Integer doctorId);
    Boolean deleteAppointment(Integer id);
    void deleteAppointmentsByDoctorId(Integer doctorId);
    Appointment confirmAppointment(Integer id);
    List<Appointment> findAllByDoctorAndDate(Integer doctorId, Date date);

    List<Appointment> findAllByDate(java.sql.Date date);
}
