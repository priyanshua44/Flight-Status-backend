package com.flightstatus.service;
import com.flightstatus.entity.Aircraft;

import java.util.List;

public interface AircraftService {
    List<Aircraft> getAllAircrafts();
    Aircraft getAircraftById(Long id);
    Aircraft saveAircraft(Aircraft aircraft);
    void deleteAircraft(Long id);
}
