package com.flightstatus.repository;

import com.flightstatus.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    @Query("SELECT DISTINCT a.name FROM Airline a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<String> findDistinctByNameContainingIgnoreCases(@Param("name") String name);

}
