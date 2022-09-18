package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTimeParser implements Parser<LocalDateTime> {
    // Datetime format example: 18/09/2022
    public static final String DATE_INPUT_PATTERN_1 = "dd/MM/yyyy";

    // Datetime format example: 2022-09-18
    public static final String DATE_INPUT_PATTERN_2 = "yyyy-MM-dd";

    // Datetime format example: 2022-09-18
    public static final String DATE_INPUT_PATTERN_3 = "MMM dd yyyy";

    // Time format example: 1800
    public static final String TIME_INPUT_PATTERN_1 = "HHmm";

    // Time format example: 06:00 PM
    public static final String TIME_INPUT_PATTERN_2 = "hh:mm a";

    public static final String TIME_OUTPUT_PATTERN = "MMM dd yyyy hh:mm a";

    @Override
    public LocalDateTime parse(String userInput) throws DukeException {
        String[] parsePatterns = new String[]{
                DATE_INPUT_PATTERN_1 + " " + TIME_INPUT_PATTERN_1,
                DATE_INPUT_PATTERN_1 + " " + TIME_INPUT_PATTERN_2,
                DATE_INPUT_PATTERN_2 + " " + TIME_INPUT_PATTERN_1,
                DATE_INPUT_PATTERN_2 + " " + TIME_INPUT_PATTERN_2,
                DATE_INPUT_PATTERN_3 + " " + TIME_INPUT_PATTERN_1,
                DATE_INPUT_PATTERN_3 + " " + TIME_INPUT_PATTERN_2
        };
        LocalDateTime dateTime = parseDateTime(userInput, parsePatterns);
        return dateTime;
    }

    private static LocalDateTime parseDateTime(String userInput, String[] parsePatterns) throws DukeException {
        boolean hasParsedSuccessfully = false;
        LocalDateTime dateTime = null;
        for (String pattern : parsePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                dateTime = LocalDateTime.parse(userInput, formatter);
            } catch (DateTimeParseException exception) {
                continue;
            }
            hasParsedSuccessfully = true;
            break;
        }

        if (!hasParsedSuccessfully) {
            throw new InvalidDateTimeFormatException();
        }

        return dateTime;
    }
}
