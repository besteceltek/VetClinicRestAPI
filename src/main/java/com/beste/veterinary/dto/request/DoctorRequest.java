package com.beste.veterinary.dto.request;

import lombok.Data;

@Data
public class DoctorRequest {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String city;
}