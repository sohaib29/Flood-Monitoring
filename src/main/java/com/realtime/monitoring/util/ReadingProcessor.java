package com.realtime.monitoring.util;

import com.realtime.monitoring.data.response.detailedReadingResponse.DetailedReadings;
import com.realtime.monitoring.data.response.frontendResponses.DetailedReadingResponse;
import com.realtime.monitoring.data.response.frontendResponses.ReadingsResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReadingProcessor {
    private final Map<String, ReadingsResponse> readingsMap;

    public ReadingProcessor() {
        this.readingsMap = initializeReadingsMap();
    }

    private Map<String, ReadingsResponse> initializeReadingsMap() {
        Map<String, ReadingsResponse> map = new HashMap<>();
        map.put("temperature", ReadingsResponse.builder().build());
        map.put("flow", ReadingsResponse.builder().build());
        map.put("windSpeed", ReadingsResponse.builder().build());
        map.put("windDirection", ReadingsResponse.builder().build());
        map.put("stageLevel", ReadingsResponse.builder().build());
        map.put("downStreamStageLevel", ReadingsResponse.builder().build());
        map.put("groundWaterLevel", ReadingsResponse.builder().build());
        map.put("tidalLevel", ReadingsResponse.builder().build());
        return map;
    }

    private void addToReadingsMap(DetailedReadings reading, String key) {
        ReadingsResponse response = readingsMap.get(key);
        response.getDateTime().add(reading.getDateTime());
        response.getValue().add(reading.getValue());
    }

    private String getKey(DetailedReadings reading) {
        String parameter = reading.getMeasure().getParameter();
        String qualifier = reading.getMeasure().getQualifier();

        if ("wind".equals(parameter) && "Speed".equals(qualifier)) {
            return "windSpeed";
        } else if ("wind".equals(parameter) && "Direction".equals(qualifier)) {
            return "windDirection";
        } else if ("level".equals(parameter) && "Stage".equals(qualifier)) {
            return "stageLevel";
        } else if ("level".equals(parameter) && "Downstream Stage".equals(qualifier)) {
            return "downStreamStageLevel";
        } else if ("level".equals(parameter) && "Groundwater".equals(qualifier)) {
            return "groundWaterLevel";
        } else if ("level".equals(parameter) && "Tidal Level".equals(qualifier)) {
            return "tidalLevel";
        } else {
            return parameter; // For temperature and flow
        }
    }

    public void processReadings(List<DetailedReadings> items) {
        for (DetailedReadings reading : items) {
            String key = getKey(reading);
            addToReadingsMap(reading, key);
        }
    }

    public DetailedReadingResponse getDetailedReadingResponse() {
        return DetailedReadingResponse.builder()
                .stageLevel(readingsMap.get("stageLevel"))
                .downStreamStageLevel(readingsMap.get("downStreamStageLevel"))
                .groundWaterLevel(readingsMap.get("groundWaterLevel"))
                .tidalLevel(readingsMap.get("tidalLevel"))
                .windSpeed(readingsMap.get("windSpeed"))
                .windDirection(readingsMap.get("windDirection"))
                .flow(readingsMap.get("flow"))
                .temperature(readingsMap.get("temperature"))
                .build();
    }
}
