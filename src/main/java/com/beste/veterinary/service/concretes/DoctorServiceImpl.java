package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.EntityRequest.DoctorRequest;
import com.beste.veterinary.dto.response.DoctorResponse;
import com.beste.veterinary.entity.Doctor;
import com.beste.veterinary.mapper.DoctorMapper;
import com.beste.veterinary.repository.DoctorRepository;
import com.beste.veterinary.service.abstracts.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final GlobalExceptionHandler globalExceptionHandler;

     @Override
    public ResponseEntity<Result> save(DoctorRequest doctorRequest) {
        Optional<Doctor> isDoctorExist = doctorRepository.
                findByPhoneAndEmail(
                        doctorRequest.getPhone(),
                        doctorRequest.getEmail()
                );
        if (isDoctorExist.isEmpty()) {
            try {
                Doctor doctor = doctorRepository.save(doctorMapper.asEntity(doctorRequest));
                return new ResponseEntity<>(ResultHelper.created(doctorMapper.asOutput(doctor)), HttpStatus.CREATED);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> update(Long id, DoctorRequest doctorRequest) {
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        Optional<Doctor> isDoctorExist = doctorRepository.findByNameAndPhoneAndEmailAndAddressAndCity(
                doctorRequest.getName(),
                doctorRequest.getPhone(),
                doctorRequest.getEmail(),
                doctorRequest.getAddress(),
                doctorRequest.getCity()
        );
        if (doctorFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundDoctorException();
        }
        if (isDoctorExist.isEmpty()) {
            try {
                Doctor doctor = doctorFromDb.get();
                doctorMapper.update(doctor, doctorRequest);
                return new ResponseEntity<>(ResultHelper.updated(doctorMapper.asOutput(doctor)), HttpStatus.OK);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        if (doctorFromDb.isPresent()) {
            try {
                doctorRepository.delete(doctorFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundDoctorException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            return new ResponseEntity<>(ResultHelper.success(doctorMapper.asOutput(doctor)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundDoctorException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<DoctorResponse> doctorList = doctorMapper.asOutput(doctorRepository.findAll());
        if (!doctorList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(doctorList), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundDoctorException();
    }
}
