package duke.ui.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDateTime {
    private static final String INPUT_FORMAT = "yyyy-MM-dd HHmm";       // {year}-{month}-{day} {hour}{minute} (24 hour)
    private static final String OUTPUT_DATE_FORMAT = "LLLL dd yyyy";    // {month in full} {day} {year}
    private static final String OUTPUT_TIME_FORMAT = "hh:mma";          // {hour}:{minute}{AM/PM}

    private LocalDateTime localDateTime;

    /**
     * Public constructor. Parses date while the object is being instantiated
     * @param unparsedDateAndTime Portion of raw arguments that represents the date and time
     * @throws DateTimeException If the raw Date and time do not follow INPUT_FORMAT
     *                           or has year, month, day, hour, minutes value(s) that are out of the logical range
     */
    public DukeDateTime(String unparsedDateAndTime) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        this.localDateTime = LocalDateTime.parse(unparsedDateAndTime, formatter);
    }

    /**
     * Formats date according to OUTPUT_DATE_FORMAT to be displayed to the user
     * @return Formatted date
     */
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
        return localDateTime.format(formatter);
    }

    /**
     * Formats time according to OUTPUT_TIME_FORMAT to be displayed to the user
     * @return Formatted time
     */
    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(OUTPUT_TIME_FORMAT);
        return localDateTime.format(formatter);
    }

}
