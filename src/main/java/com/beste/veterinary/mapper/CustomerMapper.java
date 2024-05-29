package com.beste.veterinary.mapper;

import com.beste.veterinary.dto.request.GlobalRequest.GlobalCustomerRequest;
import com.beste.veterinary.dto.request.EntityRequest.CustomerRequest;
import com.beste.veterinary.dto.response.CustomerResponse;
import com.beste.veterinary.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer asEntity(GlobalCustomerRequest globalCustomerRequest);
    Customer asEntity(CustomerRequest customerRequest);
    CustomerResponse asOutput(Customer customer);
    List<CustomerResponse> asOutput(List<Customer> customers);

    void update(@MappingTarget Customer customer, CustomerRequest customerRequest);
}
