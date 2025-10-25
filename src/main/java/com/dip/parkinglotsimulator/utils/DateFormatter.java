package com.dip.parkinglotsimulator.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    private static final String DEFAULT_DATE_TIME_VALUE = "[N/A]";
    public static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd-MM-yyyy HH:mm");

    private DateFormatter() {
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(STANDARD_DATE_TIME_FORMATTER) : DEFAULT_DATE_TIME_VALUE;
    }
}
