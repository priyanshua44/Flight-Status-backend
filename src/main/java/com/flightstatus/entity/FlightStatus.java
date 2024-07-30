package com.flightstatus.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "flight_status_details")
public class FlightStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String statusCode;
    private String description;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public FlightStatus() {
        super();
    }

    public FlightStatus(Long id, String status, String statusCode, String description, Flight flight) {
        this.id = id;
        this.status = status;
        this.statusCode = statusCode;
        this.description = description;
        this.flight = flight;
    }
}