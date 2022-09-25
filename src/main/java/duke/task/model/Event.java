package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

public class Event extends Task {
    private String datetime;

    public static final String SEPARATOR = " /at ";

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.datetime);
    }

    public static String[] extractParameters(String command) throws ArrayIndexOutOfBoundsException {
        String commandParameters = command.split(" ", 2)[1];
        return commandParameters.split(SEPARATOR);
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, "E", this.getStatusValue(), this.description, this.datetime);
    }
}
