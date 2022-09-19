package duke.task;

import duke.exception.DukeException;

/**
 * Represents task of type deadline
 */
public class Deadline extends Task {

    private String deadlineTime;

    /**
     * Constructs a Deadline object
     * Sets deadline date & time after extracting from user input
     * @param arguments user input containing task name and task date & time
     * @throws DukeException if task name or task date & time are invalid
     */
    public Deadline(String arguments) throws DukeException {
        super(arguments);
        this.deadlineTime = extractTaskTime(arguments);
    }

    public String getDeadlineTime() {
        return deadlineTime;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    @Override
    public String listTask() {
        return String.format("%s (%s)", super.listTask(), this.deadlineTime);
    }
}
