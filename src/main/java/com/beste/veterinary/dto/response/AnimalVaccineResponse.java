package com.beste.veterinary.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalVaccineResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate dateOfBirth;
}
