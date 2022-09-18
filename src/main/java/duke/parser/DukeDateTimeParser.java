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

public class DukeDateTimeParser implements Parser<LocalDateTime> {

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
