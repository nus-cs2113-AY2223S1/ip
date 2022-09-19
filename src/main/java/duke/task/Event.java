package duke.task;

import duke.exception.DukeException;

/**
 * Represents task of type event
 */
public class Event extends Task {

    private String eventTime;

    /**
     * Constructs an Event object
     * Sets event date & time after extracting from user input
     * @param arguments user input containing task name and task date & time
     * @throws DukeException if task name or task date & time are invalid
     */
    public Event(String arguments) throws DukeException {
        super(arguments);
        this.eventTime = extractTaskTime(arguments);
    }

    public String getEventTime() {
        return eventTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String listTask() {
        return String.format("%s (%s)", super.listTask(), this.eventTime);
    }
}
