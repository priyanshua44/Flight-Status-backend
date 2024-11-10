package com.flightstatus.serviceImpl;

import com.flightstatus.entity.FlightStatus;
import com.flightstatus.repository.FlightRepository;
import com.flightstatus.repository.FlightStatusRepository;
import com.flightstatus.service.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightStatusServiceImpl implements FlightStatusService {

    @Autowired
    private FlightStatusRepository flightStatusRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<FlightStatus> getAllStatuses() {
        return flightStatusRepository.findAll();
    }

    @Override
    public FlightStatus getStatusById(Long id) {
        return flightStatusRepository.findById(id).orElse(null);
    }

    @Override
    public FlightStatus saveStatus(FlightStatus status) {
        return flightStatusRepository.save(status);
    }

    @Override
    public void deleteStatus(Long id) {
        flightStatusRepository.deleteById(id);
    }

    @Override
    public List<FlightStatus> getFlightStatusesByFlightId(Long flightId) {
        return flightStatusRepository.findByFlightId(flightId);
    }


}
