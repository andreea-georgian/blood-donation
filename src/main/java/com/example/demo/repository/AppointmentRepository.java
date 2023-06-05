package com.example.demo.repository;

import com.example.demo.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByDonorId(Integer donorId);
    List<Appointment> findAllByCenterId(Integer centerId);
    List<Appointment> findAllByDoctorId(Integer doctorId);
    List<Appointment> findAllByDate(java.sql.Date date);
    List<Appointment> findAllByDoctorIdAndDate(Integer doctorId, Date date);
}
