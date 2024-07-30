package com.flightstatus.repository;


import com.flightstatus.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT DISTINCT a.name FROM Airport a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<String> findDistinctByNameContainingIgnoreCase(@Param("name") String name);
}
