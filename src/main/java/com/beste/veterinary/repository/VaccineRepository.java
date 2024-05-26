package com.beste.veterinary.repository;

import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findByNameAndCodeAndAnimalAndProtectionEndDateAfter(String name, String code, Animal entity, LocalDate protectionStartDate);

    List<Vaccine> findByAnimal_Id(Long id);

    List<Vaccine> findByProtectionEndDateBetween(LocalDate protectionEndDateStart, LocalDate protectionEndDateEnd);
}
