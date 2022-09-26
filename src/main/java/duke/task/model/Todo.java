package duke.task.model;

import duke.task.Task;
import duke.task.TaskList;

public class Todo extends Task {
    public static final String ICON = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String taskStatus) {
        super(description, taskStatus);
    }


    @Override
    public String toString() {
        return ("[" + ICON + "]" + super.toString());
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskList.FILE_STRING_SEPARATOR, ICON, this.getStatusValue(), this.description);
    }
}
