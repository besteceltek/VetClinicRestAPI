package com.beste.veterinary.dto.request;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalCustomerRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalRequest {
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String color;
    private LocalDate dateOfBirth;
    private GlobalCustomerRequest customer;
}
