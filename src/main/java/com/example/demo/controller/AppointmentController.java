package com.example.demo.controller;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.notificationfactory.NotificationSenderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentCreateDTO dto) {
        Appointment newAppointment = appointmentService.addAppointment(dto);
        NotificationSenderFactory notificationSenderFactory = new NotificationSenderFactory();
        notificationSenderFactory.create(newAppointment);
        return ResponseEntity.ok(newAppointment);
    }

    @GetMapping("/donors/{id}")
    ResponseEntity<List<Appointment>> findAllByDonor(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findAllByDonorId(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctors/{id}")
    ResponseEntity<List<Appointment>> findAllByDoctor(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findAllByDoctorId(id);
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAppointment(@PathVariable Integer id) {
        if(appointmentService.deleteAppointment(id))
            return ResponseEntity.ok("Appointment deleted");
        else return ResponseEntity.badRequest().body("Invalid date");
    }

    @GetMapping("/centers/{id}")
    ResponseEntity<List<Appointment>> findAllByCenter(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findAllByCenterId(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Appointment> confirmAppointment (@PathVariable Integer id) {
        Appointment confirmedAppointment = appointmentService.confirmAppointment(id);
        return ResponseEntity.ok(confirmedAppointment);
    }

    @GetMapping("/doctors/{id}/today")
    ResponseEntity<?> findAllByDoctorAndDate(@PathVariable Integer id) {
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        List<Appointment> appointments = appointmentService.findAllByDoctorAndDate(id, date);
        return ResponseEntity.ok(appointments);
    }

}
