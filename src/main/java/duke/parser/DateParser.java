package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The parser handles parsing of dates embedded as parameters in the user input.
 */
public class DateParser {
    public static final List<String> MONTHS_LIST = List.of("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug",
            "sep", "oct", "nov", "dec");
    public static final String MONTH_REGEX = "(" + String.join("|", MONTHS_LIST) + ")";
    public static final String YEAR_REGEX = "\\b(2\\d{3})\\b";
    public static final String DAY_REGEX = "\\b(\\d{1,2})\\b";

    /**
     * Parses a date string to a LocalDate object. Can handle date formats such as
     * 2022-12-25, 25-12-2022, 2022/12/25, 25/12/2022, 25 dec, 25 December 2022. If
     * the year is not provided, such as 25 dec, then it defaults to the current
     * year.
     * 
     * @param input The date string to be parsed
     * @return A LocalDate object, or null if the date cannot be parsed
     */
    public static LocalDate parseDateString(String input) {
        // date either does not contain spaces (e.g. 2022-01-01)
        // or has the month written out (e.g. 1 jan 2022)
        if (input.contains(" ")) {
            for (String part : input.split(" ")) {
                LocalDate attempt = parseDateString(part); // attempt to process recursively
                if (attempt != null) {
                    return attempt;
                }
            }
        } else if (input.contains("-") || input.contains("/")) {
            String[] components = input.split("[-/]");
            if (components.length != 3) {
                return null;
            }
            boolean yearFirst = components[0].length() == 4;
            int year = Integer.parseInt(yearFirst ? components[0] : components[2]);
            int month = Integer.parseInt(components[1]);
            int day = Integer.parseInt(yearFirst ? components[2] : components[0]);
            return LocalDate.of(year, month, day);
        }
        try {
            // day, month and optionally year separated by spaces
            Pattern yearPattern = Pattern.compile(YEAR_REGEX); // four digit number starting with 2
            Matcher yearMatcher = yearPattern.matcher(input);
            // default to current year
            int year = yearMatcher.find() ? Integer.parseInt(yearMatcher.group()) : LocalDate.now().getYear();
            Pattern monthPattern = Pattern.compile(MONTH_REGEX);
            Matcher monthMatcher = monthPattern.matcher(input.toLowerCase());
            if (!monthMatcher.find()) {
                return null;
            }
            int month = MONTHS_LIST.indexOf(monthMatcher.group()) + 1;
            Pattern dayPattern = Pattern.compile(DAY_REGEX);
            Matcher dayMatcher = dayPattern.matcher(input);
            if (!dayMatcher.find()) {
                return null;
            }
            int day = Integer.parseInt(dayMatcher.group());
            return LocalDate.of(year, month, day);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

}
