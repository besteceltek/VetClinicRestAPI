package com.beste.veterinary.dto.request.UpdateRequest;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalCustomerRequest;
import lombok.Data;

@Data
public class AnimalUpdateRequest {
    private String name;
    private String color;
    private GlobalCustomerRequest customer;
}
