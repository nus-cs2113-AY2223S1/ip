package duke.tasks.tasktypes;

import duke.error.exceptions.NoStateChangeException;
import duke.io.FileManager;
import duke.tasks.Task;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Deadline item that keeps track of when an item has to be completed.
 */
public class DeadlineTask extends Task {
    /** Keyword to mark when a task needs to be done by */
    public static final String COMMAND_BY = "/by";

    /** Contains text following the {@link DeadlineTask#COMMAND_BY} command. */
    private final String DEADLINE;

    /** Icon denoting type. */
    private static final String TYPE_ICON = "D";

    /**
     * Splits the text into two and initializes
     * the {@link DeadlineTask#DEADLINE} string.
     *
     * @param text input text to be parsed
     */
    public DeadlineTask(String text) {
        super(text.split(COMMAND_BY)[0]);
        DEADLINE = text.split(COMMAND_BY)[1].trim();
    }

    /**
     * Checks if command is equal to {@link DeadlineTask#TYPE_ICON}
     * @param command command string
     * @return true if it is equal
     */
    public static boolean isCommand(String command) {
        return command.trim().equals(TYPE_ICON);
    }

    /**
     * Provides type icon to be used in string formatting.
     *
     * @return Type icon "D" for "Deadline".
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Add a postfix that denotes a deadline.
     * (e.g. <code>(by 4:00 pm)</code> )
     *
     * @return postfix string
     */
    protected String getPostFix() {
        return "(by: " + DEADLINE + ")";
    }

    /**
     * Parses string loaded from save file into a {@link DeadlineTask} instance.
     *
     * @param input input to be split by separator
     * @return {@link DeadlineTask} instance
     */
    public static DeadlineTask parseDeadlineTask(String input) {
        // Parse array
        String[] sections = input.split(Pattern.quote(FileManager.getSeparator()));
        String subcommand = sections[3];
        String text = sections[2];
        String icon = sections[1];
        DeadlineTask bufferTask = new DeadlineTask(text + "/by" + subcommand);
        // Load in "done" state
        try {
            if (icon.trim().equals(Task.getStatusIcon())) {
                bufferTask.setDone(true);
            }
        } catch (NoStateChangeException e) {
            // This will never trigger as isDone is always initialized as false.
        }

        return bufferTask;
    }

    /**
     * Returns {@link List} with relevant details for saving.
     *
     * @return List of strings containing details of {@link DeadlineTask} instance.
     */
    @Override
    public List<String> getSaveItems() {
        return Arrays.asList(getTypeIcon(), getStatusIconConditional(), getText(), DEADLINE);
    }
}
