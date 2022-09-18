package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.DukeException;
import duke.Parser;

/**
 * An EventTask represents an task that happens at a certain time.
 */
public class EventTask extends Task {
    private String eventDateTime;
    private Optional<LocalDate> eventDate;
    private Optional<LocalTime> eventTime;

    /**
     * Creates a new EventTask object.
     * 
     * @param name          The name of the task
     * @param eventDateTime Date and time of the event as a string
     * @throws DukeException Throws an exception if the name or date is not provided
     */
    public EventTask(String name, String eventDateTime) throws DukeException {
        this(name, eventDateTime, false);
    }

    /**
     * Creates a new EventTask object.
     * 
     * @param name          The name of the task
     * @param eventDateTime Date and time of the event as a string
     * @param status        True if the task is done and false otherwise
     * @throws DukeException Throws an exception if the name or date is not provided
     */
    public EventTask(String name, String eventDateTime, boolean status) throws DukeException {
        super(name, status);
        if ("".equals(name)) {
            throw new DukeException("Event name cannot be empty");
        }
        if (eventDateTime == null) {
            throw new DukeException("Please provide a date and time (/at)");
        }
        this.eventDateTime = eventDateTime;
        this.eventDate = Optional.ofNullable(Parser.parseDateString(eventDateTime));
        this.eventTime = Optional.ofNullable(Parser.parseTimeString(eventDateTime));
    }

    /**
     * Formats an event task as a string to be displayed.
     */
    @Override
    public String toString() {
        String dateString = "";
        if (eventDate.isPresent()) {
            dateString = eventDate.get().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy"));
            if (eventTime.isPresent()) {
                dateString += ", " + eventTime.get().toString();
            }
        } else {
            dateString = eventDateTime;
        }
        return String.format("[E]%s (at: %s)", super.toString(), dateString);
    }

    /**
     * Formats an event task to be saved to a file.
     */
    @Override
    public String serialize() {
        return String.format("event %s /at %s /done %s", getName(), eventDateTime, isDone());
    }

}
