package com.flightstatus.service;

import com.flightstatus.entity.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> getAllAirports();
    Airport getAirportById(Long id);
    Airport saveAirport(Airport airport);
    void deleteAirport(Long id);
}
