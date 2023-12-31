package com.realtime.monitoring.data.response.singleStationResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.realtime.monitoring.util.JsonUtility;
import com.realtime.monitoring.util.LocalDateTimeListDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestReading {

    private String id;
    private String date;
    @JsonDeserialize(using = LocalDateTimeListDeserializer.class)
    private List<LocalDateTime> dateTime;
    private List<Double> value;

    @JsonCreator
    public LatestReading(
            @JsonProperty("@id") String id,
            @JsonProperty("date") String date,
            @JsonProperty("dateTime") Object dateTime,
            @JsonProperty("value") Object value
    ) {
        this.id = id;
        this.date = date;
        this.dateTime = JsonUtility.convertDateFields(dateTime);
        this.value = JsonUtility.convertDoubleFields(value);
    }

}
