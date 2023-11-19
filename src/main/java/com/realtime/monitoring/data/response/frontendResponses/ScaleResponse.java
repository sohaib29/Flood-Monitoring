package com.realtime.monitoring.data.response.frontendResponses;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ScaleResponse {

    ReadingsResponse highestRecent;
    ReadingsResponse maxOnRecord;
    ReadingsResponse minOnRecord;
    Double scaleMax;
    List<Double> typicalRangeHigh;
    List<Double> typicalRangeLow;

}
