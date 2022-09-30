import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.isBlank()) {
            throw new DukeException("☹ OOPS!!! The event date cannot be empty.");
        }
        LocalDate date;
        try {
             date = LocalDate.parse(at);
        } catch(DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Cannot parse the date");
        }
        this.at = date;
    }

    /**
     * Returns formatted string
     *
     * @return string to print
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns formatted string for saved file
     *
     * @return string to save
     */
    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + at;
    }
}
