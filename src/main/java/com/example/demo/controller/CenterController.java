package com.example.demo.controller;

import com.example.demo.entity.DonationCenter;
import com.example.demo.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    CenterService centerService;

    @GetMapping
    ResponseEntity<?> findByCounty(@RequestParam String county) {
        List<DonationCenter> donationCenters = centerService.findAllByCounty(county);
        if (donationCenters == null)
            return ResponseEntity.badRequest().body("There is no center in this county");
        else
            return ResponseEntity.ok(donationCenters);
    }
}
