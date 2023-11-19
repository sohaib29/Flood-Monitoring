package com.realtime.monitoring.data.response.multipleStationResponse;

import com.realtime.monitoring.data.response.FloodMonitoringResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultipleStationResponse extends FloodMonitoringResponse {
    private List<Items> items;
}
