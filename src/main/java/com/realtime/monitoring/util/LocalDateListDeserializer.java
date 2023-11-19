package com.realtime.monitoring.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocalDateListDeserializer extends JsonDeserializer<List<LocalDate>> {
    @Override
    public List<LocalDate> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p != null) {
            if (p.isExpectedStartArrayToken()) {
                // If it's an array, read the array elements directly
                return deserializeArray(p, ctxt);
            } else {
                // If it's a single string, proceed with the existing logic
                return deserializeString(p, ctxt);
            }
        } else {
            return null;
        }
    }

    private List<LocalDate> deserializeArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<LocalDate> localDates = new ArrayList<>();
        try {
            while (p.nextToken() != JsonToken.END_ARRAY) {
                String dateString = p.getValueAsString();
                try {
                    localDates.add(LocalDate.parse(dateString.trim(), DateTimeFormatter.ISO_DATE_TIME));
                } catch (Exception e) {
                    // Handle the exception appropriately
                    throw new IOException("Failed to parse LocalDate: " + dateString, e);
                }
            }
        } catch (Exception e) {
            // Handle the exception appropriately
            throw new IOException("Failed to parse LocalDate array", e);
        }
        return localDates;
    }

    private List<LocalDate> deserializeString(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStrings;
        String[] dateStringArray;
        try {
            dateStrings = p.readValueAs(String.class);
            dateStringArray = dateStrings.split(",");
        } catch (Exception e) {
            // Handle the exception appropriately
            throw new IOException("Failed to parse LocalDate: ", e);
        }

        List<LocalDate> localDates = new ArrayList<>();
        for (String dateString : dateStringArray) {
            try {
                localDates.add(LocalDate.parse(dateString.trim(), DateTimeFormatter.ISO_DATE_TIME));
            } catch (Exception e) {
                // Handle the exception appropriately
                throw new IOException("Failed to parse LocalDate: " + dateString, e);
            }
        }

        return localDates;
    }
}
