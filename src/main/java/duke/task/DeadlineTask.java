package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.DukeException;
import duke.parser.DateParser;
import duke.parser.TimeParser;

/**
 * A DeadlineTask represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    private static final String DEADLINE_EMPTY_ERROR_MESSAGE = "Deadline name cannot be empty";
    private static final String DEADLINE_NULL_ERROR_MESSAGE = "Please provide a deadline (/by)";
    private static final String DATE_FORMAT = "E, dd MMM yyyy";
    public static final String BY_PARAM = "by";
    public static final String KEYWORD = "deadline";
    private final String deadline;
    private final Optional<LocalDate> deadlineDate;
    private final Optional<LocalTime> deadlineTime;

    /**
     * Creates a new DeadlineTask object.
     * 
     * @param name     Name of the task
     * @param deadline Deadline of the task as a date string
     * @throws DukeException Throws an exception if either name or deadline is not
     *                       provided
     */
    public DeadlineTask(String name, String deadline) throws DukeException {
        this(name, deadline, false);
    }

    /**
     * Creates a new DeadlineTask object.
     * 
     * @param name     Name of the task
     * @param deadline Deadline of the task as a date string
     * @param status   True if the task is done and false otherwise
     * @throws DukeException Throws an exception if either name or deadline is not
     *                       provided
     */
    public DeadlineTask(String name, String deadline, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException(DEADLINE_EMPTY_ERROR_MESSAGE);
        }
        if (deadline == null) {
            throw new DukeException(DEADLINE_NULL_ERROR_MESSAGE);
        }
        this.deadline = deadline;
        this.deadlineDate = Optional.ofNullable(DateParser.parseDateString(deadline));
        this.deadlineTime = Optional.ofNullable(TimeParser.parseTimeString(deadline));
    }

    /**
     * Formats a deadline task as a string to be displayed.
     */
    @Override
    public String toString() {
        String dateString = "";
        if (deadlineDate.isPresent()) {
            dateString = deadlineDate.get().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            if (deadlineTime.isPresent()) {
                dateString += ", " + deadlineTime.get().toString();
            }
        } else {
            dateString = deadline;
        }
        return String.format("[D]%s (by: %s)", super.toString(), dateString);
    }

    /**
     * Formats a deadline task to be saved to a file.
     */
    @Override
    public String serialize() {
        return String.format("%s %s /by %s /done %s", KEYWORD, getName(), deadline, isDone());
    }

}
