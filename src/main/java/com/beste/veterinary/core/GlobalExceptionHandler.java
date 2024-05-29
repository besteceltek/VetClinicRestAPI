package com.beste.veterinary.core;

import com.beste.veterinary.core.exception.notFoundException.*;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    private String extractValidationMessage(MethodArgumentNotValidException exception) {
        String exceptionMessage = exception.getMessage();
        String[] messageParts = exceptionMessage.split(";");
        String finalPart = messageParts[messageParts.length -1];

        return finalPart.trim().replaceAll("default message \\[|]]","");
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(){
        return new ResponseEntity<>(ResultHelper.notFoundError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundAnimalException.class)
    public ResponseEntity<Result> handleNotFoundAnimalException(){
        return new ResponseEntity<>(ResultHelper.notFoundAnimalError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundDoctorException.class)
    public ResponseEntity<Result> handleNotFoundDoctorException(){
        return new ResponseEntity<>(ResultHelper.notFoundDoctorError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundCustomerException.class)
    public ResponseEntity<Result> handleNotFoundCustomerException(){
        return new ResponseEntity<>(ResultHelper.notFoundCustomerError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundAppointmentException.class)
    public ResponseEntity<Result> handleNotFoundAppointmentException(){
        return new ResponseEntity<>(ResultHelper.notFoundAppointmentError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Result>  handleAlreadyExistException(){
        return new ResponseEntity<>(ResultHelper.alreadyExistError(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Result> handleBadRequestException(String message){
        return new ResponseEntity<>(ResultHelper.badRequestError(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccineDateNotApplicableException.class)
    public ResponseEntity<Result> handleVaccineDateNotApplicableError(){
        return new ResponseEntity<>(ResultHelper.vaccineDateNotApplicableError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccineProtectionException.class)
    public ResponseEntity<Result> handleVaccineProtectionError(){
        return new ResponseEntity<>(ResultHelper.vaccineProtectionError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorIdNullException.class)
    public ResponseEntity<Result> handleDoctorIdNullException(){
        return new ResponseEntity<>(ResultHelper.doctorIdNullError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AnimalIdNullException.class)
    public ResponseEntity<Result> handleAnimalIdNullException(){
        return new ResponseEntity<>(ResultHelper.animalIdNullError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentTimeException.class)
    public ResponseEntity<Result>  handleAppointmentTimeException(){
        return new ResponseEntity<>(ResultHelper.appointmentTimeError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentExistsException.class)
    public ResponseEntity<Result>  handleAppointmentExistsException(){
        return new ResponseEntity<>(ResultHelper.appointmentExistsError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoctorNotAvailableException.class)
    public ResponseEntity<Result>  handleDoctorNotAvailableException(){
        return new ResponseEntity<>(ResultHelper.doctorNotAvailableError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppointmentNotAvailableException.class)
    public ResponseEntity<Result>  handleAppointmentNotAvailableException(){
        return new ResponseEntity<>(ResultHelper.appointmentNotAvailableError(), HttpStatus.BAD_REQUEST);
    }

}
