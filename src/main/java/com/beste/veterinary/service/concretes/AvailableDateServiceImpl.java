package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.EntityRequest.AvailableDateRequest;
import com.beste.veterinary.entity.AvailableDate;
import com.beste.veterinary.mapper.AvailableDateMapper;
import com.beste.veterinary.mapper.DoctorMapper;
import com.beste.veterinary.repository.AvailableDateRepository;
import com.beste.veterinary.service.abstracts.AvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateServiceImpl implements AvailableDateService {
    private final AvailableDateMapper availableDateMapper;
    private final AvailableDateRepository availableDateRepository;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final DoctorMapper doctorMapper;

    @Override
    public ResponseEntity<Result> save(AvailableDateRequest availableDateRequest) {
        // Check if animal selection is empty
        if (availableDateRequest.getDoctor().getId() == null) {
            return globalExceptionHandler.handleDoctorIdNullException();
        }

        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.findByAvailableDateAndDoctor(
                availableDateRequest.getAvailableDate(),
                doctorMapper.asEntity(availableDateRequest.getDoctor())
        );
        if (isAvailableDateExist.isEmpty()) {
            try {
                AvailableDate availableDate = availableDateRepository.save(availableDateMapper.asEntity(availableDateRequest));
                return new ResponseEntity<>(ResultHelper.created(availableDate), HttpStatus.CREATED);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> update(Long id, AvailableDateRequest availableDateRequest) {
        // Check if animal selection is empty
        if (availableDateRequest.getDoctor().getId() == null) {
            return globalExceptionHandler.handleDoctorIdNullException();
        }

        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);
        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.
                findByAvailableDateAndDoctor(
                        availableDateRequest.getAvailableDate(),
                        doctorMapper.asEntity(availableDateRequest.getDoctor())
        );
        if (availableDateFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundException();
        }
        if (isAvailableDateExist.isEmpty()) {
            try {
                AvailableDate availableDate = availableDateFromDb.get();
                availableDateMapper.update(availableDate, availableDateRequest);
                return new ResponseEntity<>(ResultHelper.updated(availableDate), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);
        if (availableDateFromDb.isPresent()) {
            try {
                availableDateRepository.delete(availableDateFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<AvailableDate> availableDateList = availableDateRepository.findAll();
        if (!availableDateList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(availableDateMapper.asOutput(availableDateList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        AvailableDate availableDate = availableDateRepository.findById(id).orElse(null);
        if (availableDate != null) {
            return new ResponseEntity<>(ResultHelper.success(availableDateMapper.asOutput(availableDate)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }
}
