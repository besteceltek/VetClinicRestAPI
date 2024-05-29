package com.beste.veterinary.dto.request.EntityRequest;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalDoctorRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateRequest {
    private LocalDate availableDate;
    private GlobalDoctorRequest doctor;
}
