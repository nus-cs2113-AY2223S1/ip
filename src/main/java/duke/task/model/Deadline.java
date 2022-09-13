package duke.task.model;

import duke.task.Task;
import duke.task.TaskManager;

public class Deadline extends Task {
    private String deadlineDate;

    public static final String SEPARATOR = " /by ";

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineDate);
    }

    public static String[] extractParameters(String command) throws ArrayIndexOutOfBoundsException {
        String commandParameters = command.split(" ", 2)[1];
        return commandParameters.split(SEPARATOR);
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskManager.FILE_STRING_SEPARATOR, "T", this.getStatusValue(), this.description, this.deadlineDate);
    }
}
