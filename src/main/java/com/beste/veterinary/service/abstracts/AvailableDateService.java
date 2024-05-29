package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.AvailableDateRequest;
import org.springframework.http.ResponseEntity;

public interface AvailableDateService {
    ResponseEntity<Result> save(AvailableDateRequest availableDateRequest);
    ResponseEntity<Result> update(Long id, AvailableDateRequest availableDateRequest);
    ResponseEntity<Result> delete(Long id);
    ResponseEntity<Result> findAll();
    ResponseEntity<Result> findById(Long id);
}
