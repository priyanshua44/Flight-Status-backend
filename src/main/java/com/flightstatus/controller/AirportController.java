package com.flightstatus.controller;

import com.flightstatus.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/flights/airports")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/all")
    public List<String> searchAirports(@RequestParam String query) {
        return airportRepository.findDistinctByNameContainingIgnoreCase(query);
    }
}
