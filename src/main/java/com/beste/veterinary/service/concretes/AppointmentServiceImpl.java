package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.AppointmentRequest;
import com.beste.veterinary.dto.response.AppointmentResponse;
import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Appointment;
import com.beste.veterinary.entity.AvailableDate;
import com.beste.veterinary.entity.Doctor;
import com.beste.veterinary.mapper.AnimalMapper;
import com.beste.veterinary.mapper.AppointmentMapper;
import com.beste.veterinary.mapper.DoctorMapper;
import com.beste.veterinary.repository.AnimalRepository;
import com.beste.veterinary.repository.AppointmentRepository;
import com.beste.veterinary.repository.AvailableDateRepository;
import com.beste.veterinary.repository.DoctorRepository;
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
    private final AvailableDateRepository availableDateRepository;
    private final DoctorMapper doctorMapper;
    private final AnimalMapper animalMapper;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    @Override
    public ResponseEntity<Result> save(AppointmentRequest appointmentRequest) {
        // Check if doctor selection is empty
        if (appointmentRequest.getDoctor().getId() == 0) {
            return globalExceptionHandler.handleDoctorIdNullException();
        }
        // Check if animal selection is empty
        if (appointmentRequest.getAnimal().getId() == 0) {
            return globalExceptionHandler.handleAnimalIdNullException();
        }

        Optional<Doctor> doctorFromDb = doctorRepository.findById(appointmentRequest.getDoctor().getId());
        if (doctorFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundDoctorException();
        }

        Optional<Animal> animalFromDb = animalRepository.findById(appointmentRequest.getAnimal().getId());
        if (animalFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundAnimalException();
        }

        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.
                findByAvailableDateAndDoctor(
                        appointmentRequest.getAppointmentDate().toLocalDate(),
                        doctorMapper.asEntity(appointmentRequest.getDoctor())
                );
        Optional<Appointment> isAppointmentExist = appointmentRepository.
                findByDoctorAndAnimalAndAppointmentDate(
                        doctorMapper.asEntity(appointmentRequest.getDoctor()),
                        animalMapper.asEntity(appointmentRequest.getAnimal()),
                        appointmentRequest.getAppointmentDate()
                );
        Optional<Appointment> isAppointmentTaken = appointmentRepository.
                findByDoctorAndAppointmentDate(
                        doctorMapper.asEntity(appointmentRequest.getDoctor()),
                        appointmentRequest.getAppointmentDate()
                );

        if (appointmentRequest.getAppointmentDate().getMinute() != 0) {
            globalExceptionHandler.handleAppointmentTimeException();
        }
        if (isAvailableDateExist.isEmpty()) {
            globalExceptionHandler.handleDoctorNotAvailableException();
        }
        if (isAppointmentExist.isPresent()) {
            globalExceptionHandler.handleAppointmentExistsException();
        }
        if (isAppointmentTaken.isPresent()) {
            globalExceptionHandler.handleAppointmentNotAvailableException();
        }

        Appointment appointment = appointmentRepository.save(appointmentMapper.asEntity(appointmentRequest));
        return new ResponseEntity<>(ResultHelper.created(appointmentMapper.asOutput(appointment)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Result> update(Long id, AppointmentRequest appointmentRequest) {
        return null;
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
