package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.AvailableDateRequest;
import com.beste.veterinary.service.concretes.AvailableDateServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/available-dates/")
public class AvailableDateController {
    private final AvailableDateServiceImpl availableDateService;

    public AvailableDateController(AvailableDateServiceImpl availableDateService) {
        this.availableDateService = availableDateService;
    }

    @PostMapping
    public ResponseEntity<Result> save(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.save(availableDateRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @Valid @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.update(id, availableDateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return availableDateService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return availableDateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return availableDateService.findById(id);
    }
}
