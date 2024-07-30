package com.flightstatus.controller;

import com.flightstatus.DTO.FlightDetailsResponse;
import com.flightstatus.DTO.FlightRequestDTO;
import com.flightstatus.entity.*;
import com.flightstatus.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/add")
    public Flight createFlight(@RequestBody FlightRequestDTO flightRequestDTO) {
        return flightService.save(flightRequestDTO);
    }

    @GetMapping("/search/{flightNumber}")
    public FlightDetailsResponse getFlightDetails(@PathVariable String flightNumber) {
        return flightService.getFlightDetailsByFlightNumber(flightNumber);
    }

    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

//    search by flight number and date
    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlightsByFlightNo(
            @RequestParam String flightNumber,
            @RequestParam String scheduledDepartureDate) {
        LocalDate date = LocalDate.parse(scheduledDepartureDate);
        List<Flight> flights = flightService.getFlightsByNumberAndDate(flightNumber, date);

        if (flights == null) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

//    search by airport name and date
@GetMapping("/name")
public ResponseEntity<List<Flight>> getFlightsByRoute(
        @RequestParam String departureAirportName,
        @RequestParam String arrivalAirportName,
        @RequestParam String scheduledDepartureDate) {
    LocalDate date = LocalDate.parse(scheduledDepartureDate);
    List<Flight> flights =  flightService.getFlightsByAirportAndDate(departureAirportName, arrivalAirportName, date);
    if (flights == null) {
        return ResponseEntity.ok(null);
    }

    return ResponseEntity.ok(flights);
}

    //    search by airline and date
    @GetMapping("/airline")
    public List<Flight> getFlightsByAirline(
            @RequestParam String airlineName,
            @RequestParam String scheduledDepartureDate) {
        LocalDate date = LocalDate.parse(scheduledDepartureDate);
        return flightService.getFlightsByAirlineNameAndDate(airlineName, date);
    }

//    search by airport code and date
@GetMapping("/code")
public List<Flight> getFlightsByCode(
        @RequestParam String departureAirportCode,
        @RequestParam String arrivalAirportCode,
        @RequestParam String scheduledDepartureDate) {
    LocalDate date = LocalDate.parse(scheduledDepartureDate);
    return flightService.getFlightsByAirportCodeAndDate(departureAirportCode, arrivalAirportCode, date);
}

//    search available dates
    @GetMapping("/dates")
    public List<LocalDate> getDistinctScheduledDepartureDates() {
        return flightService.getDistinctScheduledDepartureDates();
    }

}


