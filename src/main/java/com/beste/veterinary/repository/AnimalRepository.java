package com.beste.veterinary.repository;

import com.beste.veterinary.entity.Animal;
import com.beste.veterinary.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByNameAndCustomer(String name, Customer customer);

    List<Animal> findByCustomer(Customer customer);

    List<Animal> findByName(String name);

    Optional<Animal> findByNameAndColorAndCustomer(String name, String color, Customer customer);
}
