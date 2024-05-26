package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.AnimalRequest;
import com.beste.veterinary.dto.request.GlobalRequest.GlobalAnimalRequest;
import com.beste.veterinary.dto.response.AnimalResponse;
import com.beste.veterinary.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal asEntity(AnimalRequest animalRequest);
    Animal asEntity(GlobalAnimalRequest globalAnimalRequest);
    AnimalResponse asOutput(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animals);

    void update(@MappingTarget Animal animal, AnimalRequest animalRequest);
}