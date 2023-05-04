package com.example.demo.repository;

import com.example.demo.entity.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterRepository extends JpaRepository<DonationCenter, Integer> {
    Optional<List<DonationCenter>> findAllByCounty(String county);
}
