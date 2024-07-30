package com.flightstatus.controller;

import com.flightstatus.repository.AirlineRepository;
import com.flightstatus.repository.AirportRepository;
import com.flightstatus.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/flights/airlines")
public class AirlineController {

    @Autowired
    private AirlineRepository airlineRepository;

    @GetMapping("/all")
    public List<String> searchAirlines(@RequestParam String query) {
        return airlineRepository.findDistinctByNameContainingIgnoreCases(query);
    }

}
