package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.VaccineRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface VaccineService {
    public ResponseEntity<Result> save(VaccineRequest vaccineRequest);
    public ResponseEntity<Result> update(Long id, VaccineRequest vaccineRequest);
    public ResponseEntity<Result> delete(Long id);
    public ResponseEntity<Result> findById(Long id);
    public ResponseEntity<Result> findAll();
    public ResponseEntity<Result> findByAnimal(Long id);
    public ResponseEntity<Result> findByProtection(LocalDate protectionStartDate, LocalDate protectionEndDate);
}
