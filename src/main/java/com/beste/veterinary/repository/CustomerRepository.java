package com.beste.veterinary.repository;

import com.beste.veterinary.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailOrPhone(String email, String phone);

    List<Customer> findByName(String name);
}
