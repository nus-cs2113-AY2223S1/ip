package duke.exception;

public class DukeDateTimeFormatException extends Exception {

    @Override
    public String toString() {
        return "Wrong date time format. Please input date and time in yyyy-mm-dd hhmm (24 hour) format";
    }
}
