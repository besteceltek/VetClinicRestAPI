package com.beste.veterinary.repository;

import com.beste.veterinary.entity.AvailableDate;
import com.beste.veterinary.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByAvailableDateAndDoctor(LocalDate availableDate, Doctor doctor);
}
