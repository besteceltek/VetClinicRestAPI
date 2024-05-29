package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.AppointmentRequest;
import com.beste.veterinary.service.concretes.AppointmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/appointments/")
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Result> save(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.save(appointmentRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @Valid @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.update(id, appointmentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return appointmentService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @GetMapping("/find-by-doctor-appointment")
    public ResponseEntity<Result> findByDoctorAndAppointment(@RequestParam Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return appointmentService.findByAppointmentAndDoctor(id, startDate, endDate);
    }

    @GetMapping("/find-by-animal-appointment")
    public ResponseEntity<Result> findByAnimalAndAppointment(@RequestParam Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return appointmentService.findByAppointmentAndAnimal(id, startDate, endDate);
    }
}
