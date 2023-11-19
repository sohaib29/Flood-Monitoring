package com.realtime.monitoring.data.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.realtime.monitoring.data.Meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FloodMonitoringResponse {

    private String context;

    private Meta meta;

}
