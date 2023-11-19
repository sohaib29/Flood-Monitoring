package com.realtime.monitoring.services.interfaces;

public interface ExternalApiService {

    String getAllStations();

    String getStation(String stationId);

    String getStationReadings(String stationId);

}
