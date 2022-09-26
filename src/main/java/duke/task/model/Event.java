package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

public class Event extends Task {
    private final String datetime;

    public static final String ICON = "E";
    public static final String SEPARATOR = " /at ";

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Event(String description, String datetime, String status) {
        super(description, status);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", ICON, super.toString(), this.datetime);
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description, this.datetime);
    }
}
