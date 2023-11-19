package com.realtime.monitoring.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measures {
    @JsonProperty("@id")
    private String id;

    private String parameter;
    private String parameterName;
    private int period;
    private String qualifier;
    private String unitName;
    private String station;
    private String stationReference;
    private LatestReading latestReading;



}