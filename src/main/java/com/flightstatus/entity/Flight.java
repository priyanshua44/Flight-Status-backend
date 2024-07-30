package com.flightstatus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "flight_details")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private FlightStatus flightStatus;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime actualDepartureTime;
    private LocalDateTime actualArrivalTime;
    private String departureTerminal;
    private String departureGate;
    private String arrivalTerminal;
    private String arrivalGate;


    public Flight() {
        super();
    }

    public Flight(Long id, String flightNumber, Airline airline, Airport departureAirport, Airport arrivalAirport, FlightStatus flightStatus, Aircraft aircraft, LocalDateTime scheduledDepartureTime, LocalDateTime scheduledArrivalTime, LocalDateTime actualDepartureTime, LocalDateTime actualArrivalTime, String departureTerminal, String departureGate, String arrivalTerminal, String arrivalGate) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flightStatus = flightStatus;
        this.aircraft = aircraft;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.actualArrivalTime = actualArrivalTime;
        this.departureTerminal = departureTerminal;
        this.departureGate = departureGate;
        this.arrivalTerminal = arrivalTerminal;
        this.arrivalGate = arrivalGate;
    }
}