package duke.util.asset;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

public class Task {
    private boolean isDone;
    private String taskDescription;
    protected String addMessage;

    private final String STATUS_DONE_ICON = "X";
    private final String STATUS_NOTDONE_ICON = " ";

    public Task() {
        this("");
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.addMessage = "";
    }

    public String getTask() {
        return this.taskDescription;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getStatusIcon() {

        if (this.isDone) {
            return STATUS_DONE_ICON;
        } else {
            return STATUS_NOTDONE_ICON;
        }

    }

    /**
     * To format the task into human-readable form
     * @return the formatted task string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

    /**
     * To serialize the task back into the command format to be stored in storage file
     * @return formatted serialize string
     * @throws DukeException if the task parent class is being serialized
     */
    public String serialize() throws DukeException {
        throw new UnknownCommandException("Cannot serialize Task");
    }

    public String getAddMessage() throws DukeException {
        throw new UnknownCommandException("Cannot add Task");
    }

    /**
     * Check if task contains a particular keyword
     * @param keyword
     * @return if the task contains the keyword
     */
    public boolean containsKeyword(String keyword) {
        return taskDescription.toLowerCase().contains(keyword.toLowerCase());
    }
}


