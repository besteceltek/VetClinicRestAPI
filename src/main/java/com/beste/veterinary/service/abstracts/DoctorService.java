package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.DoctorRequest;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    public ResponseEntity<Result> save(DoctorRequest doctorRequest);
    public ResponseEntity<Result> update(Long id, DoctorRequest doctorRequest);
    public ResponseEntity<Result> delete(Long id);
    public ResponseEntity<Result> findById(Long id);
    public ResponseEntity<Result> findAll();
}
