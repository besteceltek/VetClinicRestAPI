package com.beste.veterinary.service.concretes;

import com.beste.veterinary.core.GlobalExceptionHandler;
import com.beste.veterinary.core.result.Result;
import com.beste.veterinary.core.result.ResultHelper;
import com.beste.veterinary.dto.request.CustomerRequest;
import com.beste.veterinary.dto.response.CustomerResponse;
import com.beste.veterinary.entity.Customer;
import com.beste.veterinary.mapper.CustomerMapper;
import com.beste.veterinary.repository.CustomerRepository;
import com.beste.veterinary.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final GlobalExceptionHandler globalExceptionHandler;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public ResponseEntity<Result> save(CustomerRequest customerRequest) {
        Optional<Customer> isCustomerExist = customerRepository.
                findByEmailOrPhone(
                        customerRequest.getEmail(),
                        customerRequest.getPhone()
                );
        if (isCustomerExist.isEmpty()) {
            try {
                Customer customer = customerRepository.save(customerMapper.asEntity(customerRequest));
                return new ResponseEntity<>(ResultHelper.created(customerMapper.asOutput(customer)), HttpStatus.CREATED);
            } catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        Optional<Customer> isCustomerExist = customerRepository.
                findByEmailOrPhone(
                        customerRequest.getEmail(),
                        customerRequest.getPhone()
                );
        if (customerFromDb.isEmpty()) {
            return globalExceptionHandler.handleNotFoundCustomerException();
        }
        if (isCustomerExist.isEmpty()) {
            try {
                Customer customer = customerFromDb.get();
                customerMapper.update(customer, customerRequest);
                return new ResponseEntity<>(ResultHelper.updated(customerMapper.asOutput(customer)), HttpStatus.OK);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleAlreadyExistException();
    }

    @Override
    public ResponseEntity<Result> delete(Long id) {
        Optional<Customer> customerFromDb = customerRepository.findById(id);
        if (customerFromDb.isPresent()) {
            try {
                customerRepository.delete(customerFromDb.get());
                return new ResponseEntity<>(ResultHelper.deleted(), HttpStatus.OK);
            }
            catch (Exception e) {
                return globalExceptionHandler.handleBadRequestException(e.getMessage());
            }
        }
        return globalExceptionHandler.handleNotFoundCustomerException();
    }

    @Override
    public ResponseEntity<Result> findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return new ResponseEntity<>(ResultHelper.success(customerMapper.asOutput(customer)), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundException();
    }

    @Override
    public ResponseEntity<Result> findAll() {
        List<CustomerResponse> customerList = customerMapper.asOutput(customerRepository.findAll());
        if (!customerList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(customerList), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundCustomerException();
    }

    @Override
    public ResponseEntity<Result> findByName(String name) {
        List<CustomerResponse> customerList = customerMapper.asOutput(customerRepository.findByName(name));
        if (!customerList.isEmpty()) {
            return new ResponseEntity<>(ResultHelper.success(customerList), HttpStatus.OK);
        }
        return globalExceptionHandler.handleNotFoundCustomerException();
    }
}
