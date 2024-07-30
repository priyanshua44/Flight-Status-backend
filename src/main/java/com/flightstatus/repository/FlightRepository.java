package com.flightstatus.repository;

import com.flightstatus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByFlightNumber(String flightNumber);

//   search by flight number
    List<Flight> findAllByFlightNumber(String flightNumber);

//    search by route
    List<Flight> findByDepartureAirportNameAndArrivalAirportName(
            @Param("departureAirportName") String departureAirportName,
            @Param("arrivalAirportName") String arrivalAirportName);

//    search by airline
    List<Flight> findByAirlineName(String airportName);

//    search by airport code
    List<Flight> findByDepartureAirportCodeAndArrivalAirportCode(
           @Param("departureAirportCode") String departureAirportCode,
           @Param("arrivalAirportCode") String arrivalAirportCode);

//    seach available dates
@Query("SELECT DISTINCT CAST(f.scheduledDepartureTime AS LocalDate) FROM Flight f ORDER BY CAST(f.scheduledDepartureTime AS LocalDate) ASC")
List<LocalDate> findDistinctScheduledDepartureDates();

}
