package com.realtime.monitoring.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private String publisher;
    private String licence;
    private String documentation;
    private String version;
    private String comment;

    @JsonProperty("hasFormat")
    private List<String> formats;

    private int limit;
}