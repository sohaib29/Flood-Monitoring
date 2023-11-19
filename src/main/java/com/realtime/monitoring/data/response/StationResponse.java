package com.realtime.monitoring.data.response;

import com.realtime.monitoring.data.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationResponse extends FloodMonitoringResponse {
    private Item items;
}
