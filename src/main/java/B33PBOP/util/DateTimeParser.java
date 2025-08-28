package B33PBOP.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter[] SUPPORTED_DATETIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"),
    };

    public static LocalDateTime parseDateTime(String dateTime) {
        for (DateTimeFormatter fmt : SUPPORTED_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTime, fmt);
            } catch (DateTimeParseException e) {
                // continue trying other formats
            }
        }
        throw new IllegalArgumentException("Invalid date/time format: " + dateTime);
    }

    public static LocalDateTime combineDateAndTime(String date, String time) {
        return parseDateTime(date + " " + time);
    }
}
