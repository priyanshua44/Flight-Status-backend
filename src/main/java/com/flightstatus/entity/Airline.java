package com.flightstatus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "airline_details")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String IATA;

    public Airline() {
        super();
    }

    public Airline(Long id, String name, String IATA) {
        this.id = id;
        this.name = name;
        this.IATA = IATA;
    }

    // Getters and Setters
}
