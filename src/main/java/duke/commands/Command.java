package duke.commands;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic command.
 */
public class Command {
    public static final int VALID_DATE_TIME_LENGTH = 2;
    public static final String SECONDS_FOR_TIME_FORMAT = ":00";

    /**
     * Checks if a given string of date and time is valid and parsable.
     *
     * @param by Date and time.
     * @throws DukeException.IllegalDateTimeException If date or time is invalid.
     */
    protected static void checkDateTime(String by) throws DukeException.IllegalDateTimeException {
        String[] arrOfBy = by.split(" ");

        if (arrOfBy.length != VALID_DATE_TIME_LENGTH) {
            throw new DukeException.IllegalDateTimeException();
        }

        arrOfBy[1] += SECONDS_FOR_TIME_FORMAT;
        try {
            LocalDate.parse(arrOfBy[0]);
            LocalTime.parse(arrOfBy[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException.IllegalDateTimeException();
        }
    }
}
