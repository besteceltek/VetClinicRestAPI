package com.beste.veterinary.dto.request.EntityRequest;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalAnimalRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRequest {
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionEndDate;
    private GlobalAnimalRequest animal;
}
