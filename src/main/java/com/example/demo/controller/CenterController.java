package com.example.demo.controller;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.DonationCenter;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/centers")
@CrossOrigin
public class CenterController {

    @Autowired
    CenterService centerService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping
    ResponseEntity<?> findByCounty(@RequestParam String county) {
        List<DonationCenter> donationCenters = centerService.findAllByCounty(county);
        if (donationCenters == null)
            return ResponseEntity.badRequest().body("There is no center in this county");
        else
            return ResponseEntity.ok(donationCenters);
    }

    @GetMapping("/{id}/valid")
    ResponseEntity<?> validDates(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findAllByCenterId(id);
        List<LocalDate> validDates = centerService.validDates(id, appointments);
        return ResponseEntity.ok(validDates);
    }
}
