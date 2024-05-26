package com.beste.veterinary.repository;

import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Appointment;
import com.beste.veterinary.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByDoctorAndAnimalAndAppointmentDate(Doctor doctor, Animal animal, LocalDateTime appointmentDate);

    Optional<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDateTime appointmentDate);

    List<Appointment> findByAnimal_IdAndAppointmentDateBetween(Long id, LocalDate appointmentDateStart, LocalDate appointmentDateEnd);

    List<Appointment> findByDoctor_IdAndAppointmentDateBetween(Long id, LocalDate startDate, LocalDate endDate);
}
