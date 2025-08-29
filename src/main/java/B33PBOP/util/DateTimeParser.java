package B33PBOP.util;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final DateTimeFormatter[] SUPPORTED_DATETIME_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
    };

    public static LocalDateTime parseDateTime(String dateTime) throws BotException {
        for (DateTimeFormatter fmt : SUPPORTED_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(dateTime, fmt);
            } catch (DateTimeParseException e) {
                // continue trying other formats
            }
        }
        throw new InvalidArgumentException("Invalid date/time format: " + dateTime + "\n");
    }

    public static LocalDateTime combineDateAndTime(String date, String time) throws BotException {
        return parseDateTime(date + " " + time);
    }
}
