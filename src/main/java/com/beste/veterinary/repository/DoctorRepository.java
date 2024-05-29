package com.beste.veterinary.repository;

import com.beste.veterinary.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByPhoneAndEmail(String phone, String email);

    Optional<Doctor> findByNameAndPhoneAndEmailAndAddressAndCity(String name, String phone, String email, String address, String city);
}
