package com.beste.veterinary.core;

import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.dto.request.EntityRequest.AppointmentRequest;
import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Appointment;
import com.beste.veterinary.entity.AvailableDate;
import com.beste.veterinary.entity.Doctor;
import com.beste.veterinary.mapper.AnimalMapper;
import com.beste.veterinary.mapper.DoctorMapper;
import com.beste.veterinary.repository.AnimalRepository;
import com.beste.veterinary.repository.AppointmentRepository;
import com.beste.veterinary.repository.AvailableDateRepository;
import com.beste.veterinary.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AppointmentValidator {
    private final AvailableDateRepository availableDateRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;
    private final DoctorMapper doctorMapper;
    private final AnimalMapper animalMapper;
    private final GlobalExceptionHandler globalExceptionHandler;

    public ResponseEntity<Result> appointmentValidate(AppointmentRequest appointmentRequest) {
        // Check if doctor selection is empty
        if (appointmentRequest.getDoctor().getId() == 0) {
            return globalExceptionHandler.handleDoctorIdNullException();
        }
        // Check if animal selection is empty
        if (appointmentRequest.getAnimal().getId() == 0) {
            return globalExceptionHandler.handleAnimalIdNullException();
        }

        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.
                findByAvailableDateAndDoctor(
                        appointmentRequest.getAppointmentDate().toLocalDate(),
                        doctorMapper.asEntity(appointmentRequest.getDoctor())
                );
        if (isAvailableDateExist.isEmpty()) {
            return globalExceptionHandler.handleDoctorNotAvailableException();
        }

        Optional<Appointment> isAppointmentExist = appointmentRepository.
                findByDoctorAndAnimalAndAppointmentDate(
                        doctorMapper.asEntity(appointmentRequest.getDoctor()),
                        animalMapper.asEntity(appointmentRequest.getAnimal()),
                        appointmentRequest.getAppointmentDate()
                );
        if (isAppointmentExist.isPresent()) {
            return globalExceptionHandler.handleAppointmentExistsException();
        }

        Optional<Appointment> isAppointmentTaken = appointmentRepository.
                findByDoctorAndAppointmentDate(
                        doctorMapper.asEntity(appointmentRequest.getDoctor()),
                        appointmentRequest.getAppointmentDate()
                );
        if (isAppointmentTaken.isPresent()) {
            return globalExceptionHandler.handleAppointmentNotAvailableException();
        }

        Optional<Doctor> doctorFromDb = doctorRepository.findById(appointmentRequest.getDoctor().getId());
        if (doctorFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundDoctorException();
        }

        Optional<Animal> animalFromDb = animalRepository.findById(appointmentRequest.getAnimal().getId());
        if (animalFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundAnimalException();
        }

        if (appointmentRequest.getAppointmentDate().getMinute() != 0) {
            return globalExceptionHandler.handleAppointmentTimeException();
        }

        return null;
    }
}
