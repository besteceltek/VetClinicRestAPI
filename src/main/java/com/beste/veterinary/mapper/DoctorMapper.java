package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalDoctorRequest;
import com.beste.veterinary.dto.request.EntityRequest.DoctorRequest;
import com.beste.veterinary.dto.response.DoctorResponse;
import com.beste.veterinary.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor asEntity(GlobalDoctorRequest globalDoctorRequest);
    Doctor asEntity(DoctorRequest doctorRequest);
    DoctorResponse asOutput(Doctor doctor);
    List<DoctorResponse> asOutput(List<Doctor> doctors);

    void update(@MappingTarget Doctor doctor, DoctorRequest doctorRequest);
}
