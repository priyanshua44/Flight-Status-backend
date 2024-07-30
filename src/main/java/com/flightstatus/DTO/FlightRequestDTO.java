package com.flightstatus.DTO;

import com.flightstatus.entity.Aircraft;
import com.flightstatus.entity.Airline;
import com.flightstatus.entity.Airport;
import com.flightstatus.entity.FlightStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FlightRequestDTO {
    private String flightNumber;
    private Airline airline;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Aircraft aircraft;
    private LocalDateTime scheduledDepartureTime;
    private LocalDateTime scheduledArrivalTime;
    private LocalDateTime actualDepartureTime;
    private LocalDateTime actualArrivalTime;
    private String departureTerminal;
    private String departureGate;
    private String arrivalTerminal;
    private String arrivalGate;
    private FlightStatus flightStatus;

    public FlightRequestDTO() {
        super();
    }

    public FlightRequestDTO(String flightNumber, Airline airline, Airport departureAirport, Airport arrivalAirport, Aircraft aircraft, LocalDateTime scheduledDepartureTime, LocalDateTime scheduledArrivalTime, LocalDateTime actualDepartureTime, LocalDateTime actualArrivalTime, String departureTerminal, String departureGate, String arrivalTerminal, String arrivalGate, FlightStatus flightStatus) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.aircraft = aircraft;
        this.scheduledDepartureTime = scheduledDepartureTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.actualDepartureTime = actualDepartureTime;
        this.actualArrivalTime = actualArrivalTime;
        this.departureTerminal = departureTerminal;
        this.departureGate = departureGate;
        this.arrivalTerminal = arrivalTerminal;
        this.arrivalGate = arrivalGate;
        this.flightStatus = flightStatus;
    }
}
