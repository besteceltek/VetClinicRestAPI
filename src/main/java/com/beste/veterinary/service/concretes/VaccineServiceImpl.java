package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.VaccineRequest;
import com.beste.veterinary.entity.Vaccine;
import com.beste.veterinary.mapper.AnimalMapper;
import com.beste.veterinary.mapper.VaccineMapper;
import com.beste.veterinary.repository.VaccineRepository;
import com.beste.veterinary.service.abstracts.VaccineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {
    private final AnimalMapper animalMapper;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    @Override
    public ResponseEntity<Result> save(VaccineRequest vaccineRequest) {
        // Check if animal selection is empty
        if (vaccineRequest.getAnimal().getId() == 0) {
            return globalExceptionHandler.handleAnimalIdNullException();
        }

        Optional<Vaccine> isVaccineExist = vaccineRepository.findByNameAndCodeAndAnimalAndProtectionEndDateAfter(
                vaccineRequest.getName(),
                vaccineRequest.getCode(),
                animalMapper.asEntity(vaccineRequest.getAnimal()),
                vaccineRequest.getProtectionStartDate()
        );

        // Check if there is a same vaccine for the animal and the protection end date is after than the protection start date of the requested vaccine
        if (isVaccineExist.isEmpty()) {
            // Check if the protection start date is after the protection end date of the requested vaccine, which is Not Applicable
            if (vaccineRequest.getProtectionStartDate().isBefore(vaccineRequest.getProtectionEndDate())) {
                try {
                    // Create a new vaccine record and save it
                    Vaccine vaccine = vaccineRepository.save(vaccineMapper.asEntity(vaccineRequest));
                    return new ResponseEntity<>(ResultHelper.created(vaccineMapper.asOutput(vaccine)), HttpStatus.CREATED);
                } catch (Exception e) {
                    return globalExceptionHandler.handleBadRequestException(e.getMessage());
                }
            }
            return globalExceptionHandler.handleVaccineDateNotApplicableError();
        }
        return globalExceptionHandler.handleVaccineProtectionError();
    }

    @Override
    public ResponseEntity<Result> update(Long id, VaccineRequest vaccineRequest) {
        // Check if animal selection is empty
        if (vaccineRequest.getAnimal().getId() == null) {
            return globalExceptionHandler.handleAnimalIdNullException();
        }

        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByNameAndCodeAndAnimalAndProtectionEndDateAfter(
                vaccineRequest.getName(),
                vaccineRequest.getCode(),
                animalMapper.asEntity(vaccineRequest.getAnimal()),
                vaccineRequest.getProtectionStartDate()
        );

        if (vaccineFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundException();
        }
        if (isVaccineExist.isEmpty()) {
            try {
                Vaccine vaccine = vaccineFromDb.get();
                vaccineMapper.update(vaccine, vaccineRequest);
                return new ResponseEntity<>(ResultHelper.updated(vaccineMapper.asOutput(vaccine)), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleVaccineProtectionError();
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isPresent()) {
            try {
                vaccineRepository.delete(vaccineFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id).orElse(null);
        if (vaccine != null) {
            return new ResponseEntity<>(ResultHelper.success(vaccineMapper.asOutput(vaccine)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<Vaccine> vaccineList = vaccineRepository.findAll();
        if (!vaccineList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.created(vaccineMapper.asOutput(vaccineList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findByAnimal(Long id) {
        List<Vaccine> vaccineList = vaccineRepository.findByAnimal_Id(id);
        if (!vaccineList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(vaccineMapper.asOutput(vaccineList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findByProtection(LocalDate protectionStartDate, LocalDate protectionEndDate) {
        if (protectionStartDate.isAfter(protectionEndDate)) {
            return globalExceptionHandler.handleVaccineDateNotApplicableError();
        }
        List<Vaccine> vaccineList = vaccineRepository.findByProtectionEndDateBetween(protectionStartDate, protectionEndDate);
        if (!vaccineList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(vaccineMapper.asOutput(vaccineList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }
}
