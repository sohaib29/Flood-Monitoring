package com.realtime.monitoring.services.interfaces;

import com.realtime.monitoring.data.Item;
import com.realtime.monitoring.data.frontendResponse.MonitoringStation;

import java.util.List;

public interface StationService {

    List<MonitoringStation> getAllStations();

    Item getStation(String id);

}
