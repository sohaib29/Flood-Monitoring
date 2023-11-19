package com.realtime.monitoring.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.realtime.monitoring.util.JsonUtility;
import com.realtime.monitoring.util.LocalDateListDeserializer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Reading {

    private String id;
    @JsonDeserialize(using = LocalDateListDeserializer.class)
    private List<LocalDate> dateTime;
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
