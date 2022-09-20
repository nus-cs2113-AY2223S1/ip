package duke.exception;

public class DukeDateTimeFormatException extends Exception {
    private String dateTimeString;

    public DukeDateTimeFormatException(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    @Override
    public String toString() {
        return "EXCEPTION: Wrong date time format\n"
                + "Your input: " + dateTimeString + "\n"
                + "DuckyMomo only understands the following:\n"
                + "  * Date and time in yyyy-mm-dd hhmm (24 hour) format\n"
                + "  * Month range is 01 <= mm <= 12\n"
                + "  * Day range is 01 <= dd <= 31 (depending on the month)\n"
                + "  * Hour range is 00 <= hh <= 23\n"
                + "  * Minute range is 00 <= mm <= 59\n";
    }
}
