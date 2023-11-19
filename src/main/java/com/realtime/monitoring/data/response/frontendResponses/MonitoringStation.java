package com.realtime.monitoring.data.response.frontendResponses;

import com.realtime.monitoring.data.enums.StationStatus;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MonitoringStation {
    String id;
    String catchment;
    List<String> label;
    String river;
    String town;
    List<String> dateOpened;
    List<StationStatus> status;
    ScaleResponse stageScale;
    ScaleResponse downstageScale;
}

