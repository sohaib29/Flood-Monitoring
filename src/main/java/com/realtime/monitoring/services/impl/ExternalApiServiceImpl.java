package com.realtime.monitoring.services.impl;

import com.realtime.monitoring.services.interfaces.ExternalApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.realtime.monitoring.util.Constant.ID;
import static com.realtime.monitoring.util.Constant.STATIONS;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {

    @Value("${external.api.url}")
    private String externalApiUrl;

    private final RestTemplate restTemplate;

    public ExternalApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("apiResponseCache")
    @Override
    public String getAllStations() {
        return restTemplate.getForObject(externalApiUrl + ID + STATIONS, String.class);
    }

    @Override
    public String getStation(String stationId) {
        return restTemplate.getForObject(externalApiUrl + ID + STATIONS + "/" + stationId, String.class);
    }

    @Override
    public String getStationReadings(String stationId) {
        return restTemplate.getForObject(externalApiUrl + ID + STATIONS + "/" + stationId + "/readings?today&_view=full&_sorted", String.class);
    }

}
