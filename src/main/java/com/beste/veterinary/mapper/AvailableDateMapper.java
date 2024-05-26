package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.AvailableDateRequest;
import com.beste.veterinary.dto.response.AvailableDateResponse;
import com.beste.veterinary.entity.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvailableDateMapper {

    AvailableDate asEntity(AvailableDateRequest availableDateRequest);
    AvailableDateResponse asOutput(AvailableDate availableDate);
    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDates);

    void update(@MappingTarget AvailableDate availableDate, AvailableDateRequest availableDateRequest);
}
