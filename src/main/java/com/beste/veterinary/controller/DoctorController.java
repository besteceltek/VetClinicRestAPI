package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.DoctorRequest;
import com.beste.veterinary.service.concretes.DoctorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Result> save(@Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.save(doctorRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @Valid @RequestBody DoctorRequest doctorRequest) {
        return doctorService.update(id, doctorRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return doctorService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return doctorService.findById(id);
    }
}
