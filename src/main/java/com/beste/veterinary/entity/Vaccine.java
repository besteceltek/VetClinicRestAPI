package com.beste.veterinary.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String code;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
}
