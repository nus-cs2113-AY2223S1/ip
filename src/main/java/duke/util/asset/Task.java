package duke.util.asset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

public class Task {
    private boolean isDone;
    private String taskDescription;
    protected String addMessage;
    //protected Set<String> taskWords;

    private final String STATUS_DONE_ICON = "X";
    private final String STATUS_NOTDONE_ICON = " ";


    public Task() {
        this("");
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.addMessage = "";

        //store each word into a hash
        //this.taskWords = new HashSet<>(Arrays.asList(taskDescription.split(" ")));
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

    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

    public String serialize() throws DukeException {
        throw new UnknownCommandException("Cannot serialize Task");
    }

    public String getAddMessage() throws DukeException {
        throw new UnknownCommandException("Cannot add Task");
    }

    public boolean containsKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }
}


