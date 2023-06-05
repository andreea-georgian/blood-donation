package com.example.demo.repository;

import com.example.demo.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    List<Donor> findAllByCounty(String county);
}
