package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Constants.DATE_INPUT_PATTERN_1;
import static duke.common.Constants.DATE_INPUT_PATTERN_2;
import static duke.common.Constants.DATE_INPUT_PATTERN_3;
import static duke.common.Constants.TIME_INPUT_PATTERN_1;
import static duke.common.Constants.TIME_INPUT_PATTERN_2;

/**
 * <code>DukeDateParser</code> is the class that parses a user input into a valid LocalDateTime.
 */
public class DukeDateTimeParser implements Parser<LocalDateTime> {
    /**
     * Parsing the user input into a valid LocalDateTime object and returns it.
     * The method will first generate a string array storing all the supported datetime format
     * that parsable into LocalDateTime by application,
     * and call a method to parse the user input using any of the supported format.
     *
     * @param userInput A string containing the datetime input given by user.
     * @return A LocalDateTime object created based on the user input.
     * @throws DukeException Exception triggered on non-parsable datetime input.
     */
    @Override
    public LocalDateTime parse(String userInput) throws DukeException {
        // All the supported datetime format that can be parsed into LocalDateTime by the application
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

    /**
     * Attempt to parse the datetime input into a LocalDateTime object using all the supported datetime format.
     * Triggers a duke exception if the string is unable to be parsed by any of the supported format.
     * Returns a LocalDateTime object if the string is successfully parsed using any of the supported format.
     *
     * @param userInput     A string containing the datetime input given by user.
     * @param parsePatterns An array of strings storing all the supported datetime format
     * @return A LocalDateTime object created based on the user input.
     * @throws DukeException Exception triggered on non-parsable datetime input.
     */
    private static LocalDateTime parseDateTime(String userInput, String[] parsePatterns) throws DukeException {
        boolean hasParsedSuccessfully = false;
        LocalDateTime dateTime = null;
        for (String pattern : parsePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                dateTime = LocalDateTime.parse(userInput, formatter);
            } catch (DateTimeParseException exception) {
                // Attempt to parse the string with next datetime format
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
