package duke.parser;

import java.time.DateTimeException;
import java.time.LocalTime;

/**
 * The parser handles parsing of times embedded as parameters in the user input.
 */
public class TimeParser {
    public static final String PM_SUFFIX = "PM";
    public static final String AM_SUFFIX = "AM";

    /**
     * Parses a date time string to a LocalTime object. The time string must contain
     * a ':' between the hours and minutes or specify am/pm/AM/PM.
     * 
     * @param input The time string to be parsed
     * @return A LocalTime object or null if the time string cannot be parsed
     */
    public static LocalTime parseTimeString(String input) {
        input = input.trim();
        // assume that the time does not contain any spaces
        // if input contains spaces, then it may contain both a
        // date component and time component, so
        // attempt to parse each component and take the one that succeeds
        if (input.contains(" ")) {
            for (String part : input.split(" ")) {
                LocalTime attempt = parseTimeString(part);
                if (attempt != null) {
                    return attempt;
                }
            }
            return null; // none of the components match the format
        }
        input = input.toUpperCase();
        // check for AM/PM suffix and strip it if present
        String suffix = "";
        if (input.endsWith(PM_SUFFIX)) {
            suffix = PM_SUFFIX;
            input = input.replace(PM_SUFFIX, "");
        } else if (input.endsWith(AM_SUFFIX)) {
            suffix = AM_SUFFIX;
            input = input.replace(AM_SUFFIX, "");
        }
        // time must either have a suffix (e.g. 2pm) or contain a ':' (e.g. 2:00) or
        // have both
        if (suffix.equals("") && !input.contains(":")) {
            return null;
        }
        try {
            String[] components = input.split(":");
            int hours = Integer.parseInt(components[0]);
            int minutes = components.length == 2 ? Integer.parseInt(components[1]) : 0;
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
