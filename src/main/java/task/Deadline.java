package task;

import exception.DukeException;

/**
 * Defines The deadline task, which has a description and a due time
 */
public class Deadline extends Task {
    protected String[] descriptions;
    protected String dueTime;

    /**
     * Initializes a deadline task with a user input
     * 
     * @param commandArgs user Input
     * @throws DukeException If user input is invalid
     */
    public Deadline(String commandArgs) throws DukeException {
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
        this.description = descriptions[0];
        this.dueTime = descriptions[1];
    }

    /**
     * Initializes a deadline task with direct information
     * (usually used when restoring task from disk)
     * 
     * @param description the description of the task
     * @param isDone      the boolean var to state whether the task is done
     * @param dueTime     the due time of the task
     */
    public Deadline(String description, boolean isDone, String dueTime) {
        super(description, isDone);
        this.dueTime = dueTime;
    }

    protected String getDueTime() {
        return this.dueTime;
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    /**
     * Assembles the information of the task to print it on screen
     * 
     * @return the assembled information
     */
    @Override
    protected String assembleResponse() {
        String response = "[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + getDescription() + "(by:"
                + getDueTime() + ")";
        return response;
    }

    /**
     * Assembles the information of the task to store it on disk
     * 
     * @return the assembled information
     */
    @Override
    public String getStorageFormat() {
        String status = isDone ? "1" : "0";
        return getTaskType() + " | " + status + " | " + description + " | " + dueTime;
    }

    /**
     * Splits the descriptions by '/by'
     * 
     * @param commandArgs the command arguments
     * @return the descriptions splited by '/by'
     * @throws DukeException If the description is invalid
     */
    protected String[] parseDescriptions(String commandArgs) throws DukeException {
        String[] descriptions = commandArgs.split("/by");
        if (descriptions.length != 2) {
            throw new DukeException(DukeException.DEADLINE_DESCRIPTION_ERROR);
        }
        return descriptions;
    }
}
