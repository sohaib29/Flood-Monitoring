package com.realtime.monitoring.data.response.singleStationResponse;

import com.realtime.monitoring.data.response.FloodMonitoringResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationResponse extends FloodMonitoringResponse {
    private Item items;
}
