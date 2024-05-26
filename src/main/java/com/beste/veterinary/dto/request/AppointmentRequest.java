package com.beste.veterinary.dto.request;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalAnimalRequest;
import com.beste.veterinary.dto.request.GlobalRequest.GlobalDoctorRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private GlobalDoctorRequest doctor;
    private GlobalAnimalRequest animal;
}
