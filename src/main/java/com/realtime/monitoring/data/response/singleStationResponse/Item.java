package com.realtime.monitoring.data.response.singleStationResponse;

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
public class Item {
    private String id;
    private List<String> rloiId;
    private String catchmentName;
    private List<String> dateOpened;
    private List<String> label;
    private List<Integer> easting;
    private List<Integer> northing;
    private List<Double> latitude;
    private List<Double> longitude;
    private List<Measures> measures;
    private String notation;
    private String riverName;
    private Scale stageScale;
    private Scale downstageScale;
    private String stationReference;
    private List<String> status;
    private String town;
    private List<String> wiskiID;


    @JsonCreator
    public Item(
            @JsonProperty("@id") String id,
            @JsonProperty("RLOIid") Object rloiId,
            @JsonProperty("catchmentName") String catchmentName,
            @JsonProperty("dateOpened") Object dateOpened,
            @JsonProperty("easting") Object easting,
            @JsonProperty("label") Object label,
            @JsonProperty("lat") Object latitude,
            @JsonProperty("long") Object longitude,
            @JsonProperty("measures") List<Measures> measures,
            @JsonProperty("northing") Object northing,
            @JsonProperty("notation") String notation,
            @JsonProperty("stageScale") Scale stageScale,
            @JsonProperty("downstageScale") Scale downstageScale,
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
        this.northing = JsonUtility.convertIntegerFields(northing);
        this.label = JsonUtility.convertStringFields(label);
        this.latitude = JsonUtility.convertDoubleFields(latitude);
        this.longitude = JsonUtility.convertDoubleFields(longitude);
        this.measures = measures;
        this.notation = notation;
        this.stageScale = checkScale(stageScale);
        this.downstageScale = checkScale(downstageScale);
        this.riverName = riverName;
        this.stationReference = stationReference;
        this.status = JsonUtility.convertStringFields(status);
        this.town = town;
        this.wiskiID = JsonUtility.convertStringFields(wiskiID);
    }

    private Scale checkScale(Object field) {
        if (field instanceof Scale) {
            return (Scale) field;
        } else {
            return null;
        }
    }


}

