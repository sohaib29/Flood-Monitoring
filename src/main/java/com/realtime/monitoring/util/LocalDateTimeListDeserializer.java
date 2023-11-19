package com.realtime.monitoring.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocalDateTimeListDeserializer extends JsonDeserializer<List<LocalDateTime>> {
    @Override
    public List<LocalDateTime> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p != null) {
            if (p.isExpectedStartArrayToken()) {
                return deserializeArray(p, ctxt);
            } else {
                return deserializeString(p, ctxt);
            }
        } else {
            return null;
        }
    }

    private List<LocalDateTime> deserializeArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        try {
            while (p.nextToken() != JsonToken.END_ARRAY) {
                String dateTimeString = p.getValueAsString();
                try {
                    localDateTimes.add(LocalDateTime.parse(dateTimeString.trim(), DateTimeFormatter.ISO_DATE_TIME));
                } catch (Exception e) {
                    throw new IOException("Failed to parse LocalDateTime: " + dateTimeString, e);
                }
            }
        } catch (Exception e) {
            throw new IOException("Failed to parse LocalDateTime array", e);
        }
        return localDateTimes;
    }

    private List<LocalDateTime> deserializeString(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateTimeStrings;
        String[] dateTimeStringArray;
        try {
            dateTimeStrings = p.readValueAs(String.class);
            dateTimeStringArray = dateTimeStrings.split(",");
        } catch (Exception e) {
            throw new IOException("Failed to parse LocalDateTime: ", e);
        }

        List<LocalDateTime> localDateTimes = new ArrayList<>();
        for (String dateTimeString : dateTimeStringArray) {
            try {
                localDateTimes.add(LocalDateTime.parse(dateTimeString.trim(), DateTimeFormatter.ISO_DATE_TIME));
            } catch (Exception e) {
                throw new IOException("Failed to parse LocalDateTime: " + dateTimeString, e);
            }
        }

        return localDateTimes;
    }
}
