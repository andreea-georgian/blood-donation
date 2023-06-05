package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "donor")
@Data
public class Donor extends User{

    private String bloodType;
    private String county;
    private int age;
    private String phoneNumber;
    private Boolean smsNotification;
    private Boolean emailNotification;
}
