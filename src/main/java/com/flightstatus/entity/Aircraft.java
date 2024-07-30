package com.flightstatus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "aircraft_details")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    private String model;

    public Aircraft() {
        super();
    }

    public Aircraft(Long id, String registrationNumber, String model) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.model = model;
    }

    // Getters and Setters
}
