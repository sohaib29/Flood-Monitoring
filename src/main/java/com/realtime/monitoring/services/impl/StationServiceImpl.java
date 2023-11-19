package com.realtime.monitoring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realtime.monitoring.data.Item;
import com.realtime.monitoring.data.enums.StationStatus;
import com.realtime.monitoring.data.frontendResponse.MonitoringStation;
import com.realtime.monitoring.data.response.MultipleStationResponse;
import com.realtime.monitoring.data.response.StationResponse;
import com.realtime.monitoring.services.interfaces.StationService;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ExternalApiServiceImpl externalApiServiceImpl;

    public StationServiceImpl(ExternalApiServiceImpl externalApiServiceImpl) {
        this.objectMapper.registerModule(new JsonldModule());
        this.objectMapper.registerModule(new JavaTimeModule());
        this.externalApiServiceImpl = externalApiServiceImpl;
    }

    @Override
    public List<MonitoringStation> getAllStations() {
        try {
            String response = this.externalApiServiceImpl.getAllStations();
            MultipleStationResponse jsonLdResponse = objectMapper.readValue(response, MultipleStationResponse.class);
            return jsonLdResponse.getItems()
                    .stream()
                    .map(item -> MonitoringStation.builder()
                            .id(getIdentifier(item.getId()))
                            .label(item.getLabel())
                            .river(item.getRiverName())
                            .town(item.getTown())
                            .dateOpened(item.getDateOpened())
                            .status(getStatus(item.getStatus()))
                            .build()
                    ).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error");
        }
    }

    private String getIdentifier(String id) {
        if (id != null) {
            return getLastElementUrl(id);
        }
        return null;
    }

    @Override
    public Item getStation(String id) {
        try {
            String response = this.externalApiServiceImpl.getStation(id);
            StationResponse jsonLdResponse = objectMapper.readValue(response, StationResponse.class);
            return jsonLdResponse.getItems();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error");
        }
    }

    @EventListener(classes = ApplicationStartedEvent.class)
    public void listenToStart(ApplicationStartedEvent event) {
        this.externalApiServiceImpl.getAllStations();
    }

    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
    public void refreshApiData() {
        this.externalApiServiceImpl.getAllStations();
    }

    private List<StationStatus> getStatus(List<String> status) {
        List<StationStatus> stationStatuses = new ArrayList<>();
        if (status != null) {
            for (String urlStatus : status) {
                switch (getLastElementUrl(urlStatus)) {
                    case "statusActive":
                        stationStatuses.add(StationStatus.ACTIVE);
                        break;
                    case "statusClosed":
                        stationStatuses.add(StationStatus.CLOSED);
                        break;
                    case "statusSuspended":
                        stationStatuses.add(StationStatus.SUSPENDED);
                        break;
                }
            }
            return stationStatuses;
        }
        return null;
    }

    private String getLastElementUrl(String urlValue) {
        String lastSegment = null;
        try {
            URL url = new URL(urlValue);
            String path = url.getPath();
            String[] pathSegments = path.split("/");
            lastSegment = pathSegments[pathSegments.length - 1];
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lastSegment;
    }

}
