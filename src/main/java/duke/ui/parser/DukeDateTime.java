package duke.ui.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime {
    private static final String INPUT_FORMAT = "yyyy-MM-dd HHmm";
    private static final String OUTPUT_DATE_FORMAT = "LLLL dd yyyy";
    private static final String OUTPUT_TIME_FORMAT = "hh:mma";

    private LocalDateTime localDateTime;

    public DukeDateTime(String unparsedDate) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        localDateTime = LocalDateTime.parse(unparsedDate, formatter);
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        return localDateTime.format(formatter);
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);
        return localDateTime.format(formatter);
    }

}
