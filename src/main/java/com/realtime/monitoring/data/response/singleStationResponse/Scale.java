package com.realtime.monitoring.data.response.singleStationResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.realtime.monitoring.util.JsonUtility;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Scale {

    private String id;
    private Reading highestRecent;
    private Reading maxOnRecord;
    private Reading minOnRecord;
    private Double scaleMax;
    private List<Double> typicalRangeHigh;
    private List<Double> typicalRangeLow;

    @JsonCreator
    public Scale(
            @JsonProperty("@id") String id,
            @JsonProperty("highestRecent") Reading highestRecent,
            @JsonProperty("maxOnRecord") Reading maxOnRecord,
            @JsonProperty("minOnRecord") Reading minOnRecord,
            @JsonProperty("scaleMax") Double scaleMax,
            @JsonProperty("typicalRangeHigh") Object typicalRangeHigh,
            @JsonProperty("typicalRangeLow") Object typicalRangeLow
    ) {
        this.id = id;
        this.highestRecent = highestRecent;
        this.minOnRecord = minOnRecord;
        this.maxOnRecord = maxOnRecord;
        this.scaleMax = scaleMax;
        this.typicalRangeHigh = JsonUtility.convertDoubleFields(typicalRangeHigh);
        this.typicalRangeHigh = JsonUtility.convertDoubleFields(typicalRangeLow);
    }

}
