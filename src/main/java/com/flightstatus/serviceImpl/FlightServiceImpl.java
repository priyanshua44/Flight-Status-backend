package com.flightstatus.serviceImpl;

import com.flightstatus.DTO.FlightDetailsResponse;
import com.flightstatus.DTO.FlightRequestDTO;
import com.flightstatus.entity.*;
import com.flightstatus.repository.*;
import com.flightstatus.service.FlightService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private FlightStatusRepository flightStatusRepository;

    @Override
    @Transactional
    public Flight save(FlightRequestDTO flightRequestDTO) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightRequestDTO.getFlightNumber());

        Airline airline = flightRequestDTO.getAirline();
        if (airline != null && airline.getId() == null) {
            airlineRepository.save(airline);
        }
        flight.setAirline(airline);

        Airport departureAirport = flightRequestDTO.getDepartureAirport();
        if (departureAirport != null && departureAirport.getId() == null) {
            airportRepository.save(departureAirport);
        }
        flight.setDepartureAirport(departureAirport);

        Airport arrivalAirport = flightRequestDTO.getArrivalAirport();
        if (arrivalAirport != null && arrivalAirport.getId() == null) {
            airportRepository.save(arrivalAirport);
        }
        flight.setArrivalAirport(arrivalAirport);

        Aircraft aircraft = flightRequestDTO.getAircraft();
        if (aircraft != null && aircraft.getId() == null) {
            aircraftRepository.save(aircraft);
        }
        flight.setAircraft(aircraft);

        FlightStatus flightStatus = flightRequestDTO.getFlightStatus();
        if (flightStatus != null && flightStatus.getId() == null) {
            flightStatusRepository.save(flightStatus);
        }
        flight.setFlightStatus(flightStatus);

        flight.setScheduledDepartureTime(flightRequestDTO.getScheduledDepartureTime());
        flight.setScheduledArrivalTime(flightRequestDTO.getScheduledArrivalTime());
        flight.setActualDepartureTime(flightRequestDTO.getActualDepartureTime());
        flight.setActualArrivalTime(flightRequestDTO.getActualArrivalTime());
        flight.setDepartureGate(flightRequestDTO.getDepartureGate());
        flight.setDepartureTerminal(flightRequestDTO.getDepartureTerminal());
        flight.setArrivalGate(flightRequestDTO.getArrivalGate());
        flight.setArrivalTerminal(flightRequestDTO.getArrivalTerminal());


        flightRepository.save(flight);

        return flight;
    }

    @Override
    public FlightDetailsResponse getFlightDetailsByFlightNumber(String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);

        if (flight == null) {
            return null;
        }

        FlightDetailsResponse response = new FlightDetailsResponse();
        response.setFlightId(flight.getId());
        response.setFlightNumber(flight.getFlightNumber());
        response.setAirline(flight.getAirline());
        response.setDepartureAirport(flight.getDepartureAirport());
        response.setArrivalAirport(flight.getArrivalAirport());
        response.setAircraft(flight.getAircraft());
        response.setFlightStatus(flight.getFlightStatus());
        response.setScheduledDepartureTime(flight.getScheduledDepartureTime());
        response.setScheduledArrivalTime(flight.getScheduledArrivalTime());
        response.setActualDepartureTime(flight.getActualDepartureTime());
        response.setActualArrivalTime(flight.getActualArrivalTime());
        response.setDepartureGate(flight.getDepartureGate());
        response.setDepartureTerminal(flight.getDepartureTerminal());
        response.setArrivalGate(flight.getArrivalGate());
        response.setArrivalTerminal(flight.getArrivalTerminal());

        return response;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

//    search by flight number and date
    @Override
    public List<Flight> getFlightsByNumberAndDate(String flightNumber, LocalDate scheduledDepartureDate) {
        // Fetch flights by flight number
        List<Flight> flights = flightRepository.findAllByFlightNumber(flightNumber);

        // Filter flights based on date part of scheduledDepartureTime
        List<Flight> filteredFlights = flights.stream()
                .filter(flight -> flight.getScheduledDepartureTime().toLocalDate().isEqual(scheduledDepartureDate))
                .collect(Collectors.toList());

        if (filteredFlights.isEmpty()) {
            return null;
        }

        return filteredFlights;
    }

//    search by route and time
    @Override
    public List<Flight> getFlightsByAirportAndDate(String departureAirportName, String arrivalAirportName, LocalDate scheduledDepartureDate) {
        List<Flight> flights = flightRepository.findByDepartureAirportNameAndArrivalAirportName(departureAirportName, arrivalAirportName);
        return flights.stream()
                .filter(flight -> flight.getScheduledDepartureTime().toLocalDate().isEqual(scheduledDepartureDate))
                .collect(Collectors.toList());
    }

//    search by airline name and time
    @Override
    public List<Flight> getFlightsByAirlineNameAndDate(String airportName, LocalDate scheduledDepartureDate){
        List<Flight> flights = flightRepository.findByAirlineName(airportName);

        List<Flight> filteredFlights =  flights.stream()
                .filter(flight -> flight.getScheduledDepartureTime().toLocalDate().isEqual(scheduledDepartureDate))
                .collect(Collectors.toList());

        if (filteredFlights.isEmpty()) {
            return null;
        }

        return filteredFlights;
    }


    //    search by airport code and date
    @Override
    public List<Flight> getFlightsByAirportCodeAndDate(String departureAirportCode, String arrivalAirportCode, LocalDate scheduledDepartureDate) {
        List<Flight> flights = flightRepository.findByDepartureAirportCodeAndArrivalAirportCode(departureAirportCode, arrivalAirportCode);

        List<Flight> filteredFlights = flights.stream()
                .filter(flight -> flight.getScheduledDepartureTime().toLocalDate().isEqual(scheduledDepartureDate))
                .collect(Collectors.toList());

        if (filteredFlights.isEmpty()) {
            return null;
        }

        return filteredFlights;
    }


    //    search available dates
public List<LocalDate> getDistinctScheduledDepartureDates() {
    return flightRepository.findDistinctScheduledDepartureDates();
}

    @Override
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
         flightRepository.deleteById(id);
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
