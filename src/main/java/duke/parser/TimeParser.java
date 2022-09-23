package duke.parser;

import java.time.DateTimeException;
import java.time.LocalTime;

public class TimeParser {

    public static final String PM_SUFFIX = "PM";
    public static final String AM_SUFFIX = "AM";

    /**
     * Parses a date time string to a LocalTime object. The time string must contain
     * a ':' between the hours and minutes and can optionally specify am/pm/AM/PM.
     * 
     * @param input The time string to be parsed
     * @return A LocalTime object or null if the time string cannot be parsed
     */
    public static LocalTime parseTimeString(String input) {
        input = input.trim();
        // if input contains spaces, then it may contain both a
        // date component and time component
        // attempt to parse each component and take the one that succeeds
        if (input.contains(" ")) {
            for (String part : input.split(" ")) {
                LocalTime attempt = parseTimeString(part);
                if (attempt != null) {
                    return attempt;
                }
            }
            return null;
        }
        // assume that the time must be formatted as HH:MM(AM|PM)?
        if (!input.contains(":")) {
            return null;
        }
        String suffix = "";
        input = input.toUpperCase();
        if (input.endsWith(PM_SUFFIX)) {
            suffix = PM_SUFFIX;
            input = input.replace(PM_SUFFIX, "");
        } else if (input.endsWith(AM_SUFFIX)) {
            suffix = AM_SUFFIX;
            input = input.replace(AM_SUFFIX, "");
        }
        try {
            String[] components = input.split(":");
            int hours = Integer.parseInt(components[0]);
            int minutes = Integer.parseInt(components[1]);
            if (suffix.equals(PM_SUFFIX)) {
                hours += 12;
            } else if (suffix.equals(AM_SUFFIX) && hours == 12) {
                hours -= 12;
            }
            return LocalTime.of(hours, minutes);
        } catch (NumberFormatException e) {
            return null;
        } catch (DateTimeException e) {
            return null;
        }
    }
}
