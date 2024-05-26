package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.VaccineRequest;
import com.beste.veterinary.dto.response.VaccineResponse;
import com.beste.veterinary.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineMapper {
    Vaccine asEntity(VaccineRequest vaccineRequest);
    VaccineResponse asOutput(Vaccine vaccine);
    List<VaccineResponse> asOutput(List<Vaccine> vaccineList);

    void update(@MappingTarget Vaccine vaccine, VaccineRequest vaccineRequest);
}
