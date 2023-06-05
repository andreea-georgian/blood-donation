package com.example.demo.controller;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Donor;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.DonorService;
import com.example.demo.service.UserService;
import com.example.demo.service.notificationfactory.NotificationSenderFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DonorService donorService;

    @PostMapping()
    ResponseEntity<?> addDoctor(@RequestBody DoctorCreateDTO dto) {
        DoctorDTO registeredDoctor = doctorService.registerDoctor(dto);
        if (registeredDoctor == null)
            return ResponseEntity.badRequest().body("This email already exists");
        else
            return ResponseEntity.ok(registeredDoctor);
    }

    @GetMapping()
    ResponseEntity<List<DoctorDTO>> allDoctors() {
        List<DoctorDTO> doctors = doctorService.allDoctors();
        return ResponseEntity.ok(doctors);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteDoctor(id);
        userService.deleteUser(id);
        appointmentService.deleteAppointmentsByDoctorId(id);
        return ResponseEntity.ok("Doctor deleted");
    }

    @PutMapping("{id}")
    ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Integer id, @RequestBody DoctorCreateDTO dto) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, dto);
        return ResponseEntity.ok(updatedDoctor);
    }

    @PutMapping("/notifications/{county}")
    ResponseEntity<?> sendNotifications(@PathVariable String county) {
        List<Donor> donors = donorService.findAllByCounty(county);
        LocalDate difDate = LocalDate.now().minusDays(183);
        Date lastDate = Date.from(difDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        NotificationSenderFactory notificationSender = new NotificationSenderFactory();
        for(Donor d:donors) {
            int ok = 1;
            List<Appointment> appointments = appointmentService.findAllByDonorId(d.getId());
            for (Appointment a:appointments) {
                if(a.getDate().compareTo(lastDate) < 0) {
                    ok = 0;
                }
            }
            if (ok == 1)
                notificationSender.sendNotification(d);
        }
        return ResponseEntity.ok("Done");
    }
}
