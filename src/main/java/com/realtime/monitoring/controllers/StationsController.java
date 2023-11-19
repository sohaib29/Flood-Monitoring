package com.realtime.monitoring.controllers;

import com.realtime.monitoring.data.Item;
import com.realtime.monitoring.data.frontendResponse.MonitoringStation;
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
    public List<MonitoringStation> getStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public Item getStation(@PathVariable("id") String stationId) {
        return stationService.getStation(stationId);
    }
}
