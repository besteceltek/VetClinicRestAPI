package com.beste.veterinary.dto.response;

import lombok.Data;

@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String city;
    private String address;
}
