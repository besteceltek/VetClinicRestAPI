package com.beste.veterinary.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private DoctorResponse doctor;
}
