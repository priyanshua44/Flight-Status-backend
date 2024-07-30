package com.flightstatus.service;

import com.flightstatus.entity.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> getAllAirlines();
    Airline getAirlineById(Long id);
    Airline saveAirline(Airline airline);
    void deleteAirline(Long id);
}
