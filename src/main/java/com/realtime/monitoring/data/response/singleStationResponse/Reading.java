package com.realtime.monitoring.data.response.singleStationResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
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
public class Reading {

    private String id;
    @JsonDeserialize(using = LocalDateTimeListDeserializer.class)
    private List<LocalDateTime> dateTime;
    private List<Double> value;

    @JsonCreator
    public Reading(
            @JsonProperty("@id") String id,
            @JsonProperty("dateTime") Object dateTime,
            @JsonProperty("value") Object value
    ) {
        this.id = id;
        this.dateTime = JsonUtility.convertDateFields(dateTime);
        this.value = JsonUtility.convertDoubleFields(value);
    }

}
