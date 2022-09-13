package duke.task.model;

import duke.task.Task;
import duke.task.TaskManager;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    public static String[] extractParameters(String command) throws ArrayIndexOutOfBoundsException {
        String commandParameters = command.split(" ", 2)[1];
        return new String[] {commandParameters};
    }

    @Override
    public String getStringForSave() {
        return String.join(TaskManager.FILE_STRING_SEPARATOR, "T", this.getStatusValue(), this.description);
    }
}
