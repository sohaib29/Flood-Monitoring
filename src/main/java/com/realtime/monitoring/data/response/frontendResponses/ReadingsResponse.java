package com.realtime.monitoring.data.response.frontendResponses;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class ReadingsResponse {

    List<LocalDateTime> dateTime;
    List<Double> value;

}
