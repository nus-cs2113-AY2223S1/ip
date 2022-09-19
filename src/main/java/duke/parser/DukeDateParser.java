package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Constants.DATE_INPUT_PATTERN_1;
import static duke.common.Constants.DATE_INPUT_PATTERN_2;
import static duke.common.Constants.DATE_INPUT_PATTERN_3;

/**
 * <code>DukeDateParser</code> is the class that parses a user input into a valid LocalDate.
 */
public class DukeDateParser implements Parser<LocalDate> {

    /**
     * Parsing the user input into a valid LocalDate object and returns it.
     * The method will first generate a string array storing all the supported date format
     * that parsable into LocalDate by application,
     * and call a method to parse the user input using any of the supported format.
     *
     * @param userInput A string containing the date input given by user.
     * @return A LocalDate object created based on the user input.
     * @throws DukeException Exception triggered on non-parsable date input.
     */
    @Override
    public LocalDate parse(String userInput) throws DukeException {
        // All the supported date format that can be parsed into LocalDate by the application
        String[] parsePatterns = new String[]{
                DATE_INPUT_PATTERN_1,
                DATE_INPUT_PATTERN_2,
                DATE_INPUT_PATTERN_3
        };
        LocalDate localDate = parseDate(userInput, parsePatterns);
        return localDate;
    }

    /**
     * Attempt to parse the date input into a LocalDate object using all the supported date format.
     * Triggers a duke exception if the string is unable to be parsed by any of the supported format.
     * Returns a LocalDate object if the string is successfully parsed using any of the supported format.
     *
     * @param userInput     A string containing the date input given by user.
     * @param parsePatterns An array of strings storing all the supported date format
     * @return A LocalDate object created based on the user input.
     * @throws DukeException Exception triggered on non-parsable date input.
     */
    private static LocalDate parseDate(String userInput, String[] parsePatterns) throws DukeException {
        boolean hasParsedSuccessfully = false;
        LocalDate date = null;
        for (String pattern : parsePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                date = LocalDate.parse(userInput, formatter);
            } catch (DateTimeParseException exception) {
                // Attempt to parse the string with next date format
                continue;
            }
            hasParsedSuccessfully = true;
            break;
        }

        if (!hasParsedSuccessfully) {
            throw new InvalidDateFormatException();
        }

        return date;
    }
}
