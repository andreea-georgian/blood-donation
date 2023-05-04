package com.example.demo.controller;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    DonorService donorService;

    @PutMapping("/{id}")
    ResponseEntity<?> updateDonor(@PathVariable Integer id, @RequestBody DonorCreateDTO dto) {
        DonorDTO updatedDonor = donorService.updateDonor(id, dto);
        return ResponseEntity.ok("Donor updated");
    }
}
