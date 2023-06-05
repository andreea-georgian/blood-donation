package com.example.demo.dto;

import com.example.demo.entity.UserRole;

public class DonorDTO {
    public int id;
    public String email;
    public String firstName;
    public String lastName;
    public String county;
    public int age;
    public String phoneNumber;
    public String bloodType;
    public UserRole.Role role;
    public Boolean smsNotification;
    public Boolean emailNotification;
}
