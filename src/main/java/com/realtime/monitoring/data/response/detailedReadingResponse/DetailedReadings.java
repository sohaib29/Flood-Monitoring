package com.realtime.monitoring.data.response.detailedReadingResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedReadings {
    @JsonProperty("@id")
    private String id;
    private LocalDateTime dateTime;
    private Measure measure;
    private Double value;
}
