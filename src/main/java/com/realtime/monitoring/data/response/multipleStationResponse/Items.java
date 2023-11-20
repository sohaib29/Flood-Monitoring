package com.realtime.monitoring.data.response.multipleStationResponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.realtime.monitoring.data.Measures;
import com.realtime.monitoring.util.JsonUtility;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    private String id;
    private String catchmentName;
    private String notation;
    private String riverName;
    private String stageScale;
    private String stationReference;
    private String town;
    private List<String> rloiId;
    private List<String> dateOpened;
    private List<String> label;
    private List<String> status;
    private List<String> wiskiID;
    private List<Double> latitude;
    private List<Integer> easting;
    private List<Double> longitude;
    private List<Integer> northing;
    private List<Measures> measures;


    @JsonCreator
    public Items(
            @JsonProperty("@id") String id,
            @JsonProperty("RLOIid") Object rloiId,
            @JsonProperty("catchmentName") String catchmentName,
            @JsonProperty("dateOpened") Object dateOpened,
            @JsonProperty("easting") Object easting,
            @JsonProperty("label") Object label,
            @JsonProperty("lat") Object latitude,
            @JsonProperty("long") Object longitude,
            @JsonProperty("measures") Object measures,
            @JsonProperty("northing") Object northing,
            @JsonProperty("notation") String notation,
            @JsonProperty("stageScale") String stageScale,
            @JsonProperty("riverName") String riverName,
            @JsonProperty("stationReference") String stationReference,
            @JsonProperty("status") Object status,
            @JsonProperty("town") String town,
            @JsonProperty("wiskiID") Object wiskiID
    ) {

        this.id = id;
        this.rloiId = JsonUtility.convertStringFields(rloiId);
        this.catchmentName = catchmentName;
        this.dateOpened = JsonUtility.convertStringFields(dateOpened);
        this.easting = JsonUtility.convertIntegerFields(easting);
        this.label = JsonUtility.convertStringFields(label);
        this.latitude = JsonUtility.convertDoubleFields(latitude);
        this.longitude = JsonUtility.convertDoubleFields(longitude);
        this.measures = JsonUtility.convertMeasureFields(measures);
        this.northing = JsonUtility.convertIntegerFields(northing);
        this.notation = notation;
        this.stageScale = stageScale;
        this.riverName = riverName;
        this.stationReference = stationReference;
        this.status = JsonUtility.convertStringFields(status);
        this.town = town;
        this.wiskiID = JsonUtility.convertStringFields(wiskiID);
    }

}

