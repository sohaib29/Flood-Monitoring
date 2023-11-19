package com.realtime.monitoring.data.response.frontendResponses;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class DetailedReadingResponse {
    ReadingsResponse stageLevel;
    ReadingsResponse downStreamStageLevel;
    ReadingsResponse groundWaterLevel;
    ReadingsResponse tidalLevel;

    ReadingsResponse windSpeed;
    ReadingsResponse windDirection;

    ReadingsResponse flow;
    ReadingsResponse temperature;
}
