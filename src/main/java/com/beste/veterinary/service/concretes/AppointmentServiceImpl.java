package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.AppointmentValidator;
import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.EntityRequest.AppointmentRequest;
import com.beste.veterinary.dto.response.AppointmentResponse;
import com.beste.veterinary.entity.Appointment;
import com.beste.veterinary.mapper.AppointmentMapper;
import com.beste.veterinary.repository.AppointmentRepository;
import com.beste.veterinary.service.abstracts.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final AppointmentValidator appointmentValidator;

    @Override
    public ResponseEntity<Result> save(AppointmentRequest appointmentRequest) {
        ResponseEntity<Result> response = appointmentValidator.appointmentValidate(appointmentRequest);
        if (response == null){
            try {
                Appointment appointment = appointmentRepository.save(appointmentMapper.asEntity(appointmentRequest));
                return new ResponseEntity<>(ResultHelper.created(appointmentMapper.asOutput(appointment)), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(ResultHelper.badRequestError(e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<Result> update(Long id, AppointmentRequest appointmentRequest) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        if (appointmentFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundAppointmentException();
        }

        ResponseEntity<Result> response = appointmentValidator.appointmentValidate(appointmentRequest);
        if (response == null){
            try {
                Appointment appointment = appointmentFromDb.get();
                appointmentMapper.update(appointment, appointmentRequest);
                return new ResponseEntity<>(ResultHelper.updated(appointmentMapper.asOutput(appointment)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(ResultHelper.badRequestError(e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        if (appointmentFromDb.isPresent()) {
            try {
                appointmentRepository.delete(appointmentFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null) {
            return new ResponseEntity<>(ResultHelper.success(appointmentMapper.asOutput(appointment)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<AppointmentResponse> appointmentList = appointmentMapper.asOutput(appointmentRepository.findAll());
        if (!appointmentList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(appointmentList), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findByAppointmentAndAnimal(Long id, LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointmentList = appointmentRepository.
                findByAnimal_IdAndAppointmentDateBetween(id, startDate, endDate);
        if (!appointmentList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(appointmentMapper.asOutput(appointmentList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findByAppointmentAndDoctor(Long id, LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointmentList = appointmentRepository.
                findByDoctor_IdAndAppointmentDateBetween(id, startDate, endDate);
        if (!appointmentList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(appointmentMapper.asOutput(appointmentList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }
}
