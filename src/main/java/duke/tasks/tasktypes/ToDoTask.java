package duke.tasks.tasktypes;

import duke.error.exceptions.NoStateChangeException;
import duke.io.Storage;
import duke.tasks.Task;

import java.util.regex.Pattern;

/**
 * Vanilla tasks. Task item without any extra functionality.
 */
public class ToDoTask extends Task {

    /**
     * Icon denoting task type.
     */
    private static final String TASK_TYPE_ICON = "T";

    public ToDoTask(String text) {
        super(text);
    }

    /**
     * Checks if command is equal to {@link ToDoTask#TASK_TYPE_ICON}
     *
     * @param command command string
     * @return true if it is equal
     */
    public static boolean isCommand(String command) {
        return command.trim().equals(TASK_TYPE_ICON);
    }

    /**
     * Parses string loaded from save file into a {@link ToDoTask} instance.
     *
     * @param input input to be split by separator
     * @return {@link ToDoTask} instance
     */
    public static ToDoTask parseToDoTask(String input) {
        // Parse array
        String[] sections = input.split(Pattern.quote(Storage.getSeparator()));
        String text = sections[2];
        String icon = sections[1];
        ToDoTask bufferTask = new ToDoTask(text);
        // Load in "done" state
        try {
            if (icon.trim().equals("X")) {
                bufferTask.setDone(true);
            }
        }
        catch (NoStateChangeException e) {
            // This will never trigger as isDone is always initialized as false.
        }
        return bufferTask;
    }

    /**
     * Provides type icon to be used in string formatting.
     *
     * @return Type icon "T" for "ToDo".
     */
    @Override
    public String getTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
