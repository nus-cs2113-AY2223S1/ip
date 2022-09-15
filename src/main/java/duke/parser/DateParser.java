package duke.parser;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    public String formatDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
    }

    public LocalDate convertStringDatetoLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean isValidDate(String date) throws DukeException {
        try {
            LocalDate dateTest = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! I cannot recognize the date\n" + "Follow this format: yyyy-mm-dd");
        }
        return true;
    }
}
