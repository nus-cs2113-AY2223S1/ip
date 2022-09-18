package duke.commands;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Command {
    public static final int VALID_DATE_TIME_LENGTH = 2;
    public static final String SECONDS_FOR_TIME_FORMAT = ":00";

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
