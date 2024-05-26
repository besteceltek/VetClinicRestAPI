package com.beste.veterinary.controller;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.CustomerRequest;
import com.beste.veterinary.service.concretes.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Result> save(@RequestBody CustomerRequest customerRequest) {
        return customerServiceImpl.save(customerRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        return customerServiceImpl.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Long id) {
        return customerServiceImpl.delete(id);
    }

    @GetMapping
    public ResponseEntity<Result> findAll() {
        return customerServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findById(@PathVariable Long id) {
        return customerServiceImpl.findById(id);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<Result> findByName(@RequestParam String name) {
        return customerServiceImpl.findByName(name);
    }
}
