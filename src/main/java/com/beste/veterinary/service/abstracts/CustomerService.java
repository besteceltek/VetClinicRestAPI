package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.CustomerRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public ResponseEntity<Result> save(CustomerRequest customerRequest);
    public ResponseEntity<Result> update(Long id, CustomerRequest customerRequest);
    public ResponseEntity<Result> delete(Long id);
    public ResponseEntity<Result> findById(Long id);
    public ResponseEntity<Result> findAll();
    public ResponseEntity<Result> findByName(String name);
}
