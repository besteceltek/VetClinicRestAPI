package com.beste.veterinary.service.abstracts;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.AppointmentRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface AppointmentService {
    public ResponseEntity<Result> save(AppointmentRequest appointmentRequest);
    public ResponseEntity<Result> update(Long id, AppointmentRequest appointmentRequest);
    public ResponseEntity<Result> delete(Long id);
    public ResponseEntity<Result> findById(Long id);
    public ResponseEntity<Result> findAll();

    ResponseEntity<Result> findByAppointmentAndAnimal(Long id, LocalDate startDate, LocalDate endDate);

    ResponseEntity<Result> findByAppointmentAndDoctor(Long id, LocalDate startDate, LocalDate endDate);
}
