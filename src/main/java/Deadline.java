import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (by.isBlank()) {
            throw new DukeException();
        }
        LocalDate date;
        try {
            date = LocalDate.parse(by);
        } catch(DateTimeParseException e) {
            throw new DukeException();
        }
        this.by = date;    }

    /**
     * Returns formatted string
     *
     * @return string to print
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns formatted string for saved file
     *
     * @return string to save
     */
    @Override
    public String toSaveString() {
        return "D|" + super.toSaveString() + "|" + by;
    }
}

