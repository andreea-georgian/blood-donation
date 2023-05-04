package com.example.demo.controller;

import com.example.demo.dto.AppointmentCreateDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    ResponseEntity<Appointment> addAppointment(@RequestBody AppointmentCreateDTO dto) {
        Appointment newAppointment = appointmentService.addAppointment(dto);
        return ResponseEntity.ok(newAppointment);
    }

    @GetMapping("/donors/{id}")
    ResponseEntity<List<Appointment>> findAllByDonor(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findAllByDonorId(id);
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted");
    }

    @GetMapping("/centers/{id}")
    ResponseEntity<List<Appointment>> findAllByCenter(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findAllByCenterId(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Appointment> confirmAppointment (@PathVariable Integer id, @RequestParam Integer doctorId) {
        Appointment confirmedAppointment = appointmentService.confirmAppointment(id, doctorId);
        return ResponseEntity.ok(confirmedAppointment);
    }
}
