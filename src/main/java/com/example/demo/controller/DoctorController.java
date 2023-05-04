package com.example.demo.controller;

import com.example.demo.dto.DoctorCreateDTO;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.service.DoctorService;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;

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
        return ResponseEntity.ok("Doctor deleted");
    }

    @PutMapping("{id}")
    ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Integer id, @RequestBody DoctorCreateDTO dto) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, dto);
        return ResponseEntity.ok(updatedDoctor);
    }
}
