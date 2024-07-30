package com.flightstatus.repository;

import com.flightstatus.DTO.FlightDetailsResponse;
import com.flightstatus.entity.Flight;
import com.flightstatus.entity.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightStatusRepository extends JpaRepository<FlightStatus, Long> {
    List<FlightStatus> findByFlightId(Long flightId);

    List<FlightStatus> findByFlight(Flight flight);
}
