package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.AnimalRequest;
import org.springframework.http.ResponseEntity;

public interface AnimalService {
    public ResponseEntity<Result> save(AnimalRequest animalRequest);
    public ResponseEntity<Result> update(Long id, AnimalRequest animalRequest);
    public ResponseEntity<Result> delete(Long id);
    public ResponseEntity<Result> findById(Long id);
    public ResponseEntity<Result> findAll();
    public ResponseEntity<Result> findByName(String name);
    public ResponseEntity<Result> findByCustomer(Long customerId);
}
