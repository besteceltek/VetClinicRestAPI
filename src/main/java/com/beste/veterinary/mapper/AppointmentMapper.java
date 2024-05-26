package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.AppointmentRequest;
import com.beste.veterinary.dto.response.AppointmentResponse;
import com.beste.veterinary.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment asEntity(AppointmentRequest appointmentRequest);
    List<AppointmentResponse> asOutput(List<Appointment> appointment);
    AppointmentResponse asOutput(Appointment appointment);

    void update(@MappingTarget Appointment appointment, AppointmentRequest appointmentRequest);
}
