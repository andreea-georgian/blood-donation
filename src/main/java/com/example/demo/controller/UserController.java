package com.example.demo.controller;

import com.example.demo.dto.UserLoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserRole;
import com.example.demo.service.DonorService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    DonorService donorService;

    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody UserLoginDTO dto) {
        UserDTO loginUser = userService.loginUser(dto);
        if (loginUser == null)
            return ResponseEntity.badRequest().body("User inexistent");
        else {
            if (loginUser.role.equals(UserRole.Role.donor))
                return ResponseEntity.ok(donorService.findById(loginUser.id));
            else
                return ResponseEntity.ok(loginUser);
        }
    }
}
