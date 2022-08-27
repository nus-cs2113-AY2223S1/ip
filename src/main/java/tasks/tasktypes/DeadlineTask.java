package tasks.tasktypes;

import tasks.Task;

/**
 * Deadline item that keeps track of when an item has to be completed.
 */
public class DeadlineTask extends Task {
    /** Keyword to mark when a task needs to be done by */
    public static final String COMMAND_BY = "/by";

    /** Contains text following the {@link DeadlineTask#COMMAND_BY} command. */
    private final String DEADLINE;

    /**
     * Splits the text into two and initializes both {@link DeadlineTask#TEXT} and
     * {@link DeadlineTask#DEADLINE} strings.
     * @param text input text to be parsed
     */
    public DeadlineTask(String text) {
        super(text.split(COMMAND_BY)[0]);
        DEADLINE = text.split(COMMAND_BY)[1].trim();
    }

    /**
     * Provides type icon to be used in string formatting.
     * @return Type icon "D" for "Deadline".
     */
    @Override
    protected String getTypeIcon() {
        return "D";
    }

    /**
     * Add a postfix that denotes a deadline.
     * (e.g. <code>(by 4:00 pm)</code> )
     * @return postfix string
     */
    protected String getPostFix() {
        return "(by: " + DEADLINE + ")";
    }
}
