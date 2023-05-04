package com.example.demo.controller;

import com.example.demo.dto.DonorCreateDTO;
import com.example.demo.dto.DonorDTO;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody UserLoginDTO dto) {
        UserDTO loginUser = userService.loginUser(dto);
        if (loginUser == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(loginUser);
    }
}
