package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DateTimeParser {
    public static final List<String> MONTHS_LIST = List.of("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug",
            "sep", "oct", "nov", "dec");
    public static final String MONTH_REGEX = "(" + String.join("|", MONTHS_LIST) + ")";
    public static final String YEAR_REGEX = "\\b(2\\d{3})\\b";
    public static final String DAY_REGEX = "\\b(\\d{1,2})\\b";
    private static final String HOURS_REGEX = "\\b(\\d{1,2})\\b";
    private static final String MINUTES_REGEX = "\\b(\\d{1,2})\\b";
    private static final String AMPM_REGEX = "(am|pm)";


    /**
     * Parses a date string into a LocalDate object. The date string must be in the format of "dd MMM yyyy".
     *
     * @param input The date string to be parsed
     * @return A LocalDate object, or null if the date cannot be parsed
     */

    public static LocalDate parseDate(String input) {
        LocalDate date = null;
        String[] dateParts = input.split(" ");
        String day = null;
        String month = null;
        String year = null;
        for (String part : dateParts) {
            if (part.matches(DAY_REGEX)) {
                day = part;
            } else if (part.matches(MONTH_REGEX)) {
                month = part;
            } else if (part.matches(YEAR_REGEX)) {
                year = part;
            }
        }
        if (day != null && month != null && year != null) {
            date = LocalDate.parse(year + "-" + month + "-" + day);
        } else if (day != null && month != null) {
            date = LocalDate.parse(LocalDate.now().getYear() + "-" + month + "-" + day);
        }
        return date;
    }

    /**
     * Parses a date time string to a LocalTime object. The time string must contain
     * a ':' between the hours and minutes or specify am/pm/AM/PM.
     *
     * @param input The time string to be parsed
     * @return A LocalTime object or null if the time string cannot be parsed
     */
    public static LocalTime parseTime(String input) {
        LocalTime time = null;
        String[] timeParts = input.split(" ");
        String hours = null;
        String minutes = null;
        String ampm = null;
        for (String part : timeParts) {
            if (part.matches(HOURS_REGEX)) {
                hours = part;
            } else if (part.matches(MINUTES_REGEX)) {
                minutes = part;
            } else if (part.matches(AMPM_REGEX)) {
                ampm = part;
            }
        }
        if (hours != null && minutes != null) {
            if (ampm != null) {
                if (ampm.equals("pm") || ampm.equals("PM")) {
                    hours = String.valueOf(Integer.parseInt(hours) + 12);
                }
            }
            time = LocalTime.parse(hours + ":" + minutes);
        }
        return time;
    }
}