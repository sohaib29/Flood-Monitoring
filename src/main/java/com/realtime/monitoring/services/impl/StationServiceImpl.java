package com.realtime.monitoring.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.realtime.monitoring.data.enums.StationStatus;
import com.realtime.monitoring.data.response.detailedReadingResponse.DetailedReadings;
import com.realtime.monitoring.data.response.detailedReadingResponse.DetailedReadingsResponse;
import com.realtime.monitoring.data.response.frontendResponses.*;
import com.realtime.monitoring.data.response.multipleStationResponse.MultipleStationResponse;
import com.realtime.monitoring.data.response.singleStationResponse.Item;
import com.realtime.monitoring.data.response.singleStationResponse.Reading;
import com.realtime.monitoring.data.response.singleStationResponse.Scale;
import com.realtime.monitoring.data.response.singleStationResponse.StationResponse;
import com.realtime.monitoring.services.interfaces.StationService;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public List<Stations> getAllStations() {
        try {
            String response = this.externalApiServiceImpl.getAllStations();
            MultipleStationResponse jsonLdResponse = objectMapper.readValue(response, MultipleStationResponse.class);
            return jsonLdResponse.getItems()
                    .stream()
                    .map(item -> Stations.builder()
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
    public MonitoringStation getStation(String id) {
        try {
            String response = this.externalApiServiceImpl.getStation(id);
            StationResponse jsonLdResponse = objectMapper.readValue(response, StationResponse.class);
            Item item = jsonLdResponse.getItems();

            return MonitoringStation.builder()
                    .id(getIdentifier(item.getId()))
                    .catchment(item.getCatchmentName())
                    .label(item.getLabel())
                    .river(item.getRiverName())
                    .town(item.getTown())
                    .dateOpened(item.getDateOpened())
                    .status(getStatus(item.getStatus()))
                    .stageScale(getScaleResponse(item.getStageScale()))
                    .downstageScale(getScaleResponse(item.getDownstageScale()))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error");
        }
    }

    @Override
    public DetailedReadingResponse getStationReadings(String id) {
        try {
            String response = this.externalApiServiceImpl.getStationReadings(id);
            DetailedReadingsResponse jsonLdResponse = objectMapper.readValue(response, DetailedReadingsResponse.class);
            List<DetailedReadings> items = jsonLdResponse.getItems();

            List<LocalDateTime> temperatureDate = new ArrayList<>();
            List<Double> temperatureValue = new ArrayList<>();

            List<LocalDateTime> flowDate = new ArrayList<>();
            List<Double> flowValue = new ArrayList<>();

            List<LocalDateTime> windSpeedDate = new ArrayList<>();
            List<Double> windSpeedValue = new ArrayList<>();

            List<LocalDateTime> windDirectionDate = new ArrayList<>();
            List<Double> windDirectionValue = new ArrayList<>();

            List<LocalDateTime> stageLevelDate = new ArrayList<>();
            List<Double> stageLevelValue = new ArrayList<>();

            List<LocalDateTime> downStreamStageLevelDate = new ArrayList<>();
            List<Double> downStreamStageLevelValue = new ArrayList<>();

            List<LocalDateTime> groundWaterLevelDate = new ArrayList<>();
            List<Double> groundWaterLevelValue = new ArrayList<>();

            List<LocalDateTime> tidalLevelDate = new ArrayList<>();
            List<Double> tidalLevelValue = new ArrayList<>();


            for (DetailedReadings reading : items) {
                if (Objects.equals("temperature", reading.getMeasure().getParameter())) {
                    temperatureDate.add(reading.getDateTime());
                    temperatureValue.add(reading.getValue());
                } else if (Objects.equals("flow", reading.getMeasure().getParameter())) {
                    flowDate.add(reading.getDateTime());
                    flowValue.add(reading.getValue());
                } else if (Objects.equals("wind", reading.getMeasure().getParameter()) &&
                        Objects.equals("Speed", reading.getMeasure().getQualifier())) {
                    windSpeedDate.add(reading.getDateTime());
                    windSpeedValue.add(reading.getValue());
                } else if (Objects.equals("wind", reading.getMeasure().getParameter()) &&
                        Objects.equals("Direction", reading.getMeasure().getQualifier())) {
                    windDirectionDate.add(reading.getDateTime());
                    windDirectionValue.add(reading.getValue());
                } else if (Objects.equals("level", reading.getMeasure().getParameter()) &&
                        Objects.equals("Stage", reading.getMeasure().getQualifier())) {
                    stageLevelDate.add(reading.getDateTime());
                    stageLevelValue.add(reading.getValue());
                } else if (Objects.equals("level", reading.getMeasure().getParameter()) &&
                        Objects.equals("Downstream Stage", reading.getMeasure().getQualifier())) {
                    downStreamStageLevelDate.add(reading.getDateTime());
                    downStreamStageLevelValue.add(reading.getValue());
                } else if (Objects.equals("level", reading.getMeasure().getParameter()) &&
                        Objects.equals("Groundwater", reading.getMeasure().getQualifier())) {
                    groundWaterLevelDate.add(reading.getDateTime());
                    groundWaterLevelValue.add(reading.getValue());
                } else if (Objects.equals("level", reading.getMeasure().getParameter()) &&
                        Objects.equals("Tidal Level", reading.getMeasure().getQualifier())) {
                    tidalLevelDate.add(reading.getDateTime());
                    tidalLevelValue.add(reading.getValue());
                }
            }


            return DetailedReadingResponse.builder()
                    .stageLevel(readingsResponseBuilder(stageLevelDate, stageLevelValue))
                    .downStreamStageLevel(readingsResponseBuilder(downStreamStageLevelDate, downStreamStageLevelValue))
                    .groundWaterLevel(readingsResponseBuilder(groundWaterLevelDate, groundWaterLevelValue))
                    .tidalLevel(readingsResponseBuilder(tidalLevelDate, tidalLevelValue))
                    .windSpeed(readingsResponseBuilder(windSpeedDate, windSpeedValue))
                    .windDirection(readingsResponseBuilder(windDirectionDate, windDirectionValue))
                    .flow(readingsResponseBuilder(flowDate, flowValue))
                    .temperature(readingsResponseBuilder(temperatureDate, temperatureValue))
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error");
        }
    }


    private ReadingsResponse readingsResponseBuilder(List<LocalDateTime> dateTime, List<Double> value) {
        if (!dateTime.isEmpty() && !value.isEmpty()) {
            return ReadingsResponse.builder()
                    .dateTime(dateTime)
                    .value(value)
                    .build();
        }
        return null;
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

    private ScaleResponse getScaleResponse(Scale scale) {
        if (scale != null) {
            return ScaleResponse.builder()
                    .scaleMax(scale.getScaleMax())
                    .typicalRangeLow(scale.getTypicalRangeLow())
                    .typicalRangeHigh(scale.getTypicalRangeHigh())
                    .highestRecent(getReadingResponse(scale.getHighestRecent()))
                    .maxOnRecord(getReadingResponse(scale.getMaxOnRecord()))
                    .minOnRecord(getReadingResponse(scale.getMaxOnRecord()))
                    .build();

        }
        return null;
    }

    private ReadingsResponse getReadingResponse(Reading reading) {
        if (reading != null) {
            return ReadingsResponse.builder()
                    .dateTime(reading.getDateTime())
                    .value(reading.getValue())
                    .build();
        }
        return null;
    }

}
