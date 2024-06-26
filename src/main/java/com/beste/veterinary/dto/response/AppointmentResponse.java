package com.beste.veterinary.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private DoctorResponse doctor;
    private AnimalResponse animal;
}
