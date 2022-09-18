package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.common.Constants.DATE_INPUT_PATTERN_1;
import static duke.common.Constants.DATE_INPUT_PATTERN_2;
import static duke.common.Constants.DATE_INPUT_PATTERN_3;

public class DukeDateParser implements Parser<LocalDate> {

    @Override
    public LocalDate parse(String userInput) throws DukeException {
        String[] parsePatterns = new String[]{
                DATE_INPUT_PATTERN_1,
                DATE_INPUT_PATTERN_2,
                DATE_INPUT_PATTERN_3
        };
        LocalDate localDate = parseDate(userInput, parsePatterns);
        return localDate;
    }

    private static LocalDate parseDate(String userInput, String[] parsePatterns) throws DukeException {
        boolean hasParsedSuccessfully = false;
        LocalDate date = null;
        for (String pattern : parsePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                date = LocalDate.parse(userInput, formatter);
            } catch (DateTimeParseException exception) {
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
