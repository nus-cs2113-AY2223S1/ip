package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

public class Deadline extends Task {
    private String deadlineDate;

    public static final String ICON = "D";
    public static final String SEPARATOR = " /by ";

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public Deadline(String description, String deadlineDate, String status) {
        super(description, status);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ICON, super.toString(), this.deadlineDate);
    }

    public static String[] extractParameters(String command) throws ArrayIndexOutOfBoundsException {
        String commandParameters = command.split(" ", 2)[1];
        return commandParameters.split(SEPARATOR);
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description, this.deadlineDate);
    }
}
