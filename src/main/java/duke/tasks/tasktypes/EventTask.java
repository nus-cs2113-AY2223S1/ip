package duke.tasks.tasktypes;

import duke.error.exceptions.NoStateChangeException;
import duke.io.Storage;
import duke.tasks.Task;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Event item that keeps track of when an item is slated to happen.
 */
public class EventTask extends Task {
    /**
     * Keyword to mark when an event is supposed to happen
     */
    public static final String COMMAND_AT = "/at";

    /**
     * Icon denoting task type.
     */
    private static final String TYPE_ICON = "E";

    /**
     * Contains text following the {@link EventTask#COMMAND_AT} command.
     */
    private final String EVENT_DATE;

    /**
     * Splits the text into two and initializes
     * the {@link EventTask#EVENT_DATE} string.
     *
     * @param text input text to be parsed
     */
    public EventTask(String text) {
        super(text.split(COMMAND_AT)[0]);
        EVENT_DATE = text.split(COMMAND_AT)[1].trim();
    }

    /**
     * Checks if command is equal to {@link EventTask#TYPE_ICON}
     *
     * @param command command string
     * @return true if it is equal
     */
    public static boolean isCommand(String command) {
        return command.trim().equals(TYPE_ICON);
    }

    /**
     * Parses string loaded from save file into a {@link EventTask} instance.
     *
     * @param input input to be split by separator
     * @return {@link EventTask} instance
     */
    public static EventTask parseEventTask(String input) {
        // Parse array
        String[] sections = input.split(Pattern.quote(Storage.getSeparator()));
        String subcommand = sections[3];
        String text = sections[2];
        String icon = sections[1];
        EventTask bufferTask = new EventTask(text + COMMAND_AT + subcommand);
        // Load in "done" state
        try {
            if (icon.trim().equals("X")) {
                bufferTask.setDone(true);
            }
        } catch (NoStateChangeException e) {
            // This will never trigger as isDone is always initialized as false.
        }

        return bufferTask;
    }

    /**
     * Provides type icon to be used in string formatting.
     *
     * @return Type icon "E" for "Event".
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Add a postfix that denotes when something happens
     * (e.g. <code>(at 3:00 pm)</code> )
     *
     * @return postfix string
     */
    protected String getPostFix() {
        return "(at: " + EVENT_DATE + ")";
    }

    /**
     * Returns {@link List} with relevant details for saving.
     *
     * @return List of strings containing details of {@link EventTask} instance.
     */
    @Override
    public List<String> getSaveItems() {
        return Arrays.asList(getTypeIcon(), getStatusIconSave(), getText(), EVENT_DATE);
    }

    /**
     * Returns event date string.
     *
     * @return Event date string
     */
    public String getEventDate() {
        return EVENT_DATE;
    }
}
