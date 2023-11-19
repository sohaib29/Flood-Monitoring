package com.realtime.monitoring.controllers;

import com.realtime.monitoring.data.response.frontendResponses.DetailedReadingResponse;
import com.realtime.monitoring.data.response.frontendResponses.MonitoringStation;
import com.realtime.monitoring.data.response.frontendResponses.Stations;
import com.realtime.monitoring.services.interfaces.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/stations")
@RequiredArgsConstructor
public class StationsController {

    private final StationService stationService;

    @GetMapping()
    public List<Stations> getStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public MonitoringStation getStation(@PathVariable("id") String stationId) {
        return stationService.getStation(stationId);
    }

    @GetMapping("/{id}/readings")
    public DetailedReadingResponse getStationReadings(@PathVariable("id") String stationId) {
        return stationService.getStationReadings(stationId);
    }
}
