package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static String formatDateToString(String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return parsedDate.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
    }

    public static LocalDate convertStringDateToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate dateTest = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
