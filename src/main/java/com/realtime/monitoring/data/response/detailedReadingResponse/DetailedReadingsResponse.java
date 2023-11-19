package com.realtime.monitoring.data.response.detailedReadingResponse;

import com.realtime.monitoring.data.response.FloodMonitoringResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetailedReadingsResponse extends FloodMonitoringResponse {
    private List<DetailedReadings> items;
}
