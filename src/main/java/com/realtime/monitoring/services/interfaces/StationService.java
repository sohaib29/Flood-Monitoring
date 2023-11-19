package com.realtime.monitoring.services.interfaces;

import com.realtime.monitoring.data.response.frontendResponses.DetailedReadingResponse;
import com.realtime.monitoring.data.response.frontendResponses.MonitoringStation;
import com.realtime.monitoring.data.response.frontendResponses.Stations;

import java.util.List;

public interface StationService {

    List<Stations> getAllStations();
    MonitoringStation getStation(String id);
    DetailedReadingResponse getStationReadings(String id);

}
