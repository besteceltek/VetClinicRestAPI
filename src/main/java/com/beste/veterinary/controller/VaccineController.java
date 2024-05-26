package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.VaccineRequest;
import com.beste.veterinary.service.concretes.VaccineServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/vaccines/")
public class VaccineController {
    private final VaccineServiceImpl vaccineService;

    public VaccineController(VaccineServiceImpl vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping
    public ResponseEntity<Result> save(@RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.save(vaccineRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody VaccineRequest vaccineRequest) {
        return vaccineService.update(id, vaccineRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return vaccineService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return vaccineService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return vaccineService.findById(id);
    }

    @GetMapping("/find-by-animal")
    public ResponseEntity<Result> findByAnimal(@RequestParam Long id) {
        return vaccineService.findByAnimal(id);
    }

    @GetMapping("/find-by-protection")
    public ResponseEntity<Result> findByProtection(@RequestParam LocalDate protectionStartDate, @RequestParam LocalDate protectionEndDate) {
        return vaccineService.findByProtection(protectionStartDate, protectionEndDate);
    }
}
