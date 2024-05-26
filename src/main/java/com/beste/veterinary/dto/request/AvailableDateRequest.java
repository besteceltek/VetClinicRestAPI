package com.beste.veterinary.dto.request;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalDoctorRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateRequest {
    private LocalDate availableDate;
    private GlobalDoctorRequest doctor;
}
