package com.realtime.monitoring.util;

import com.realtime.monitoring.data.Measures;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class JsonUtility {

    public static List<String> convertStringFields(Object field) {
        return convertFields(field, String.class);
    }

    public static List<Integer> convertIntegerFields(Object field) {
        return convertFields(field, Integer.class);
    }

    public static List<Double> convertDoubleFields(Object field) {
        return convertFields(field, Double.class);
    }

    public static List<Measures> convertMeasureFields(Object field) {
        return convertFields(field, Measures.class);
    }

    public static List<LocalDateTime> convertDateFields(Object field) {
        return convertFields(field, LocalDateTime.class);
    }

    public static <T> List<T> convertFields(Object field, Class<T> type) {
        if (field instanceof List) {
            return (List<T>) field;
        } else if (type.isInstance(field)) {
            return Collections.singletonList((T) field);
        } else {
            return null;
        }
    }


}
