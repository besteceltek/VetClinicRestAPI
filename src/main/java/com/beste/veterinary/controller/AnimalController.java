package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.AnimalRequest;
import com.beste.veterinary.dto.request.UpdateRequest.AnimalUpdateRequest;
import com.beste.veterinary.service.concretes.AnimalServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/animals/")
public class AnimalController {
    private final AnimalServiceImpl animalService;

    public AnimalController(AnimalServiceImpl animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Result> save(@RequestBody AnimalRequest animalRequest) {
        return animalService.save(animalRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        return animalService.update(id, animalUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return animalService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Result> findByName(@RequestParam String name) {
        return animalService.findByName(name);
    }

    @GetMapping("/find-by-customer")
    public ResponseEntity<Result> findByCustomer(@RequestParam Long customerId) {
        return animalService.findByCustomer(customerId);
    }
}
