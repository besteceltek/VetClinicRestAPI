package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.AnimalRequest;
import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Customer;
import com.beste.veterinary.mapper.AnimalMapper;
import com.beste.veterinary.mapper.CustomerMapper;
import com.beste.veterinary.repository.AnimalRepository;
import com.beste.veterinary.repository.CustomerRepository;
import com.beste.veterinary.service.abstracts.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final CustomerMapper customerMapper;
    private final GlobalExceptionHandler globalExceptionHandler;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<Result> save(AnimalRequest animalRequest) {
        Optional<Animal> isAnimalExist = animalRepository.findByNameAndCustomer(
                animalRequest.getName(),
                customerMapper.asEntity(animalRequest.getCustomer())
        );
        if (isAnimalExist.isEmpty()) {
            try {
                Animal animal = animalRepository.save(animalMapper.asEntity(animalRequest));
                return new ResponseEntity<>(ResultHelper.created(animalMapper.asOutput(animal)), HttpStatus.CREATED);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> update(Long id, AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        Optional<Animal> isAnimalExist = animalRepository.findByNameAndCustomer(
                animalRequest.getName(),
                customerMapper.asEntity(animalRequest.getCustomer())
        );
        if (animalFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundAnimalException();
        }
        if (isAnimalExist.isEmpty()) {
            try {
                Animal animal = animalFromDb.get();
                animalMapper.update(animal, animalRequest);
                return new ResponseEntity<>(ResultHelper.updated(animalMapper.asOutput(animal)), HttpStatus.OK);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isPresent()) {
            try {
                animalRepository.delete(animalFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundAnimalException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal != null) {
            return new ResponseEntity<>(ResultHelper.success(animalMapper.asOutput(animal)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundAnimalException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<Animal> animalList = animalRepository.findAll();
        if (!animalList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(animalMapper.asOutput(animalList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundAnimalException();
    }

    @Override
    public ResponseEntity<Result> findByName(String name) {
        List<Animal> animalList = animalRepository.findByName(name);
        if (!animalList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(animalMapper.asOutput(animalList)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundAnimalException();
    }

    @Override
    public ResponseEntity<Result> findByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            List<Animal> animalList = animalRepository.findByCustomer(customer);
            if (!animalList.isEmpty()) {
                return new ResponseEntity<>(ResultHelper.success(animalMapper.asOutput(animalList)), HttpStatus.OK);
            }
            return globalExceptionHandler.handleNotFoundAnimalException();
        }
        return globalExceptionHandler.handleNotFoundCustomerException();
    }
}
