package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.DukeException;
import duke.Parser;

public class DeadlineTask extends Task {
    private final String deadline;
    private final LocalDate deadlineDate;
    private final Optional<LocalTime> deadlineTime;

    public DeadlineTask(String name, String deadline) throws DukeException {
        this(name, deadline, false);
    }

    public DeadlineTask(String name, String deadline, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException("Deadline name cannot be empty");
        }
        if (deadline == null) {
            throw new DukeException("Please provide a deadline (/by)");
        }
        this.deadline = deadline;
        this.deadlineDate = Parser.parseDateString(deadline);
        if (this.deadlineDate == null) {
            throw new DukeException("Sorry, I don't understand this date");
        }
        this.deadlineTime = Optional.ofNullable(Parser.parseTimeString(deadline));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s%s)", super.toString(),
                deadlineDate.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy")),
                deadlineTime.isPresent() ? " at " + deadlineTime.get().toString() : "");
    }

    @Override
    public String serialize() {
        return String.format("deadline %s /by %s /done %s", getName(), deadline, isDone());
    }

}
