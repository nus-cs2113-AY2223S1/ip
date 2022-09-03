package duke.tasks.tasktypes;

import duke.tasks.Task;

/**
 * Vanilla tasks. Task item without any extra functionality.
 */
public class ToDoTask extends Task {
    public ToDoTask(String text) {
        super(text);
    }

    /**
     * Provides type icon to be used in string formatting.
     *
     * @return Type icon "T" for "ToDo".
     */
    @Override
    protected String getTypeIcon() {
        return "T";
    }
}
