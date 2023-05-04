package com.example.demo.controller;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.service.DonorService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donors")
public class DonorController {

    @Autowired
    DonorService donorService;
    UserService userService;

    @PutMapping("/{id}")
    ResponseEntity<DonorDTO> updateDonor(@PathVariable Integer id, @RequestBody DonorCreateDTO dto) {
        DonorDTO updatedDonor = donorService.updateDonor(id, dto);
        return ResponseEntity.ok(updatedDonor);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteDonor (@PathVariable Integer id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok("Donor deleted");
    }

    @PostMapping()
    ResponseEntity<?> registerUser(@RequestBody DonorCreateDTO dto) {
        DonorDTO registeredDonor = userService.registerUser(dto);
        if (registeredDonor == null)
            return ResponseEntity.badRequest().body("This email already exists");
        else
            return ResponseEntity.ok(registeredDonor);
    }
}
