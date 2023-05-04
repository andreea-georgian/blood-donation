package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctor")
@Data
public class Doctor extends User{

    @ManyToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;
}
