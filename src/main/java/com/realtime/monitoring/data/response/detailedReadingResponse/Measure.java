package com.realtime.monitoring.data.response.detailedReadingResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure {
    @JsonProperty("@id")
    private String id;

    private String parameter;
    private int period;
    private String qualifier;
    private String unitName;
    private Station station;
    private String stationReference;
    private String valueType;

}