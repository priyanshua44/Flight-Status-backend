package com.flightstatus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "airport_details")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private String city;

    private String country;

    public Airport() {
        super();
    }

    public Airport(Long id, String code, String name, String city, String country) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    // Getters and Setters
}